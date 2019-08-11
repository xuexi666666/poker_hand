package sales;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SalesAppTest {
	public static final String SALES_ACTIVITY = "SalesActivity";
	@Mock
	SalesDao salesDao;
	@Mock
	SalesReportDao salesReportDao;
	@Mock
	EcmService ecmService;
	@InjectMocks
	SalesApp salesApp = new SalesApp();
    Sales sales;

	@Before
	public void init(){
		//given
		sales = createMockSales("554456789",false);
	}

	@Test
	public void testGenerateReport() {
		//given
		Sales sales = createMockSales("554456789",true);
		SalesApp salesApp = spy(this.salesApp);
		List<SalesReportData> salesReportDataList = new ArrayList<>();
		SalesReportData salesReportData = mock(SalesReportData.class);

		salesReportDataList.add(salesReportData);
		salesReportDataList.add(salesReportData);
		salesReportDataList.add(salesReportData);
		salesReportDataList.add(salesReportData);
		salesReportDataList.add(salesReportData);
		salesReportDataList.add(salesReportData);

		SalesActivityReport salesActivityReport = mock(SalesActivityReport.class);
		String resXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
					"<note>\n" + "</note>";

		//when
		doReturn(true).when(salesApp).isCorrectTimeRange(any());
		when(sales.isSupervisor()).thenReturn(true);
		doReturn(sales).when(salesApp).getSalesById("554456789");
		when(salesReportDao.getReportData(sales)).thenReturn(salesReportDataList);
		when(salesReportData.getType()).thenReturn(SALES_ACTIVITY);
        doReturn(salesActivityReport).when(salesApp).generateReport(salesApp.headers,salesReportDataList);
        when(salesActivityReport.toXml()).thenReturn(resXml);
        doNothing().when(ecmService).uploadDocument(resXml);

        //then
		salesApp.generateSalesActivityReport(sales,5,true);
		verify(salesApp,times(1)).getSalesById(sales.getSalesId());
		verify(salesReportDao,times(1)).getReportData(sales);
		verify(salesApp,times(1)).filterReportData(salesReportDataList,true,5);
		verify(salesApp,times(5)).isSalesActivity(any());
		verify(salesApp,times(5)).isSalesReportData(salesReportData,true);
		verify(salesApp,times(1)).generateReport(salesApp.headers,salesReportDataList);
		verify(salesActivityReport,times(1)).toXml();
		verify(ecmService,times(1)).uploadDocument(resXml);
		Assert.assertEquals(5,salesApp.filteredReportDataList.size());
	}

	@Test
	public void should_return_sales_when_given_correct_salesId(){
		//when
		when(salesDao.getSalesBySalesId("554456789")).thenReturn(sales);
		Sales result = salesApp.getSalesById("554456789");
		//then
		verify(salesDao,times(1)).getSalesBySalesId("554456789");
		Assert.assertEquals(sales.getSalesId(),result.getSalesId());
	}

	@Test
	public void should_return_false_when_given_is_not_correct_salesId(){
		//when
		when(salesDao.getSalesBySalesId("554456789")).thenReturn(sales);
		Sales result1 = salesApp.getSalesById("554456788");
		Sales result2 = salesApp.getSalesById(null);
		//then
		verify(salesDao,times(1)).getSalesBySalesId("554456788");
		Assert.assertNull(result1);
		Assert.assertNull(result2);
	}

	@Test
	public void should_return_true_when_given_correct_time(){
		//when
		when(sales.getEffectiveTo()).thenReturn(new Date(new Date().getTime()+60*60*24*1000));
		when(sales.getEffectiveFrom()).thenReturn(new Date(new Date().getTime()-60*60*24*1000));
		boolean result = salesApp.isCorrectTimeRange(sales);
		//then
		verify(sales,times(1)).getEffectiveFrom();
		verify(sales,times(1)).getEffectiveTo();
		Assert.assertTrue(result);
	}
	@Test
	public void should_return_false_when_given_not_effectiveto_correct_time(){
		//when
		when(sales.getEffectiveTo()).thenReturn(new Date(new Date().getTime()-60*60*24*1000));
		boolean result = salesApp.isCorrectTimeRange(sales);
		//then
		verify(sales,times(1)).getEffectiveTo();
		Assert.assertFalse(result);
	}

	@Test
	public void should_return_false_when_given_not_effectivefrom_correct_time(){
		//when
		when(sales.getEffectiveTo()).thenReturn(new Date(new Date().getTime()+60*60*24*1000));
		when(sales.getEffectiveFrom()).thenReturn(new Date(new Date().getTime()+60*60*24*1000));
		boolean result = salesApp.isCorrectTimeRange(sales);
		//then
		verify(sales,times(1)).getEffectiveFrom();
		verify(sales,times(1)).getEffectiveTo();
		Assert.assertFalse(result);
	}

	@Test
	public void should_return_true_when_give_not_supervisor_sales_and_not_confidential(){
		//given
		SalesReportData salesReportData = mock(SalesReportData.class);
		//when
		when(salesReportData.isConfidential()).thenReturn(false);
		boolean result = salesApp.isSalesReportData(salesReportData,sales.isSupervisor());
		//then
		verify(salesReportData,times(1)).isConfidential();
		Assert.assertTrue(result);
	}

	@Test
	public void should_return_true_when_give_supervisor_sales_and_confidential(){
		//given
		Sales sales = createMockSales("554456789",true);
		SalesReportData salesReportData = mock(SalesReportData.class);
		//when
		when(salesReportData.isConfidential()).thenReturn(true);
		boolean result = salesApp.isSalesReportData(salesReportData,sales.isSupervisor());
		//then
		verify(salesReportData,times(1)).isConfidential();
		Assert.assertTrue(result);
	}

	@Test
	public void should_return_false_when_give_not_supervisor_sales_and_confidential(){
		//given
		SalesReportData salesReportData = mock(SalesReportData.class);
		//when
		when(salesReportData.isConfidential()).thenReturn(true);
		boolean result = salesApp.isSalesReportData(salesReportData,sales.isSupervisor());
		//then
		verify(salesReportData,times(1)).isConfidential();
		Assert.assertFalse(result);
	}

	@Test
	public void should_return_true_when_give_sales_activity(){
		//given
		SalesReportData salesReportData1 = mock(SalesReportData.class);
		SalesReportData salesReportData2 = mock(SalesReportData.class);
		//when+then
		when(salesReportData1.getType()).thenReturn(SALES_ACTIVITY);
		boolean result = salesApp.isSalesActivity(salesReportData1);
		verify(salesReportData1,times(1)).getType();
		Assert.assertTrue(result);

		//when+then
		when(salesReportData2.getType()).thenReturn(SALES_ACTIVITY.toLowerCase());
		boolean res = salesApp.isSalesActivity(salesReportData2);
		verify(salesReportData2,times(1)).getType();
		Assert.assertTrue(res);

	}

	@Test
	public void should_return_filteredreportdatalist_when_given_reportdatalist_and_issupervisor(){
		//given
		SalesReportData salesReportData = mock(SalesReportData.class);
		List<SalesReportData> salesReportDataList = new ArrayList<>();
		salesReportDataList.add(salesReportData);
		salesReportDataList.add(salesReportData);
		salesReportDataList.add(salesReportData);
		salesReportDataList.add(salesReportData);
		salesReportDataList.add(salesReportData);
		salesReportDataList.add(salesReportData);
		//when
		when(salesReportData.isConfidential()).thenReturn(false);
		when(salesReportData.getType()).thenReturn(SALES_ACTIVITY);
		when(salesReportDao.getReportData(sales)).thenReturn(salesReportDataList);
		salesApp.filterReportData(salesReportDao.getReportData(sales),sales.isSupervisor(),5);
		//then
		verify(salesReportData,times(5)).getType();
		verify(salesReportData,times(5)).isConfidential();

		Assert.assertEquals(5,salesApp.filteredReportDataList.size());
		Assert.assertNotNull(salesApp.filteredReportDataList.get(0));
	}

	@Test
	public void should_return_report_when_give_headers_and_reportdatalist(){
		//given
		List<SalesReportData> salesReportDataList = mock(ArrayList.class);
		List<String> headers = mock(ArrayList.class);
		SalesActivityReport salesActivityReport = mock(SalesActivityReport.class);
		SalesApp salesApp = spy(this.salesApp);
		//when
		doReturn(salesActivityReport).when(salesApp).generateReport(headers,salesReportDataList);
		//then
		SalesActivityReport res = salesApp.generateReport(headers,salesReportDataList);
		Assert.assertNotNull(res);
		Assert.assertEquals(res,salesActivityReport);
	}

	private Sales createMockSales(String salesId,boolean isSupervisor) {
		Sales sales = mock(Sales.class);
		when(sales.getSalesId()).thenReturn(salesId);
		when(sales.isSupervisor()).thenReturn(isSupervisor);
		return sales;
	}

}
