package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {
    public static final String SALES_ACTIVITY = "SalesActivity";
    public static final String TIME = "Time";
    public static final String LOCAL_TIME = "Local Time";
    SalesDao salesDao;
    SalesReportDao salesReportDao;
    EcmService ecmService;
    List<SalesReportData> filteredReportDataList = new ArrayList<>();
    List<String> headers = new ArrayList<>(Arrays.asList("Sales ID", "Sales Name", "Activity"));

    public SalesApp() {
        salesDao = new SalesDao();
        salesReportDao = new SalesReportDao();
        ecmService = new EcmService();
    }

    public void generateSalesActivityReport(Sales sale, int maxRow, boolean isNatTrade) {
//        List<SalesReportData> tempList = new ArrayList<SalesReportData>();
        Sales sales = getSalesById(sale.getSalesId());
        if (!isCorrectTimeRange(sales)) {
            return;
        }
        List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);
        //对这段代码有疑问,为啥对reportDataList进行过滤了得到filteredReportDataList以后
        //还要将初始化的templist赋值给filteredReportDataList
        //那么过滤的意义何在,况且当maxRow>reportDataList.size(),还会造成数组越界异常
        //我的想法是下面这段代码删除,我对这个maxRow的理解是允许从reportDataList拿到的最大数量(不知道对不对)
        //		for (int i=0; i < reportDataList.size() || i < maxRow; i++) {
//			tempList.add(reportDataList.get(i));
//		}
//		filteredReportDataList = tempList;
        //
        //所以我对这段代码重构假设是取两者容量的最小值:Math.min(reportDataList.size(),maxRow)
        //然后在filterReportData中限制所取数据的数量
        filterReportData(reportDataList, sales.isSupervisor(), maxRow);
        headers.add(isNatTrade ? TIME : LOCAL_TIME);
        SalesActivityReport report = generateReport(headers, reportDataList);
        ecmService.uploadDocument(report.toXml());
    }

    protected Sales getSalesById(String salesId) {
//		if (salesId == null) {
//			return null;
//		}
        return salesId == null ? null : salesDao.getSalesBySalesId(salesId);
    }

    protected boolean isCorrectTimeRange(Sales sales) {
        Date today = new Date();
        return (today.after(sales.getEffectiveTo()) || today.before(sales.getEffectiveFrom())) ? false : true;
    }

    public void filterReportData(List<SalesReportData> reportDataList, boolean isSupervisor, int maxRow) {
        //原版
        //		for (SalesReportData data : reportDataList) {
//			if (SALES_ACTIVITY.equalsIgnoreCase(data.getType())) {
//				if (!data.isConfidential()) {
//					filteredReportDataList.add(data);
//					if (isSupervisor) {
//						filteredReportDataList.add(data);
//					}
//				}else {
//					filteredReportDataList.add(data);
//				}
//			}
//		}

        //版本迭代第2次
//		for (SalesReportData data : reportDataList) {
//			if (SALES_ACTIVITY.equalsIgnoreCase(data.getType())) {
//				if (!data.isConfidential()||isSupervisor) {
//					filteredReportDataList.add(data);
//				}
//			}
//		}

        //版本迭代第3次
//		reportDataList.stream()
//				.filter(data ->SALES_ACTIVITY.equalsIgnoreCase(data.getType()))
//				.filter(data ->(!data.isConfidential()||isSupervisor) )
//				.forEach(data -> filteredReportDataList.add(data));

        //版本迭代第4次
//		reportDataList.stream()
//				.filter(data ->isSalesActivity(data)&&isSalesReportData(data,isSupervisor))
//				.forEach(data ->filteredReportDataList.add(data));
        //版本迭代第5次
        int maxCapacity = Math.min(reportDataList.size(), maxRow);
        reportDataList.stream()
                .limit(maxCapacity)
                .filter(data -> isSalesActivity(data) && isSalesReportData(data, isSupervisor))
                .forEach(data -> filteredReportDataList.add(data));

    }

    protected boolean isSalesActivity(SalesReportData data) {
        return SALES_ACTIVITY.equalsIgnoreCase(data.getType());
    }

    protected boolean isSalesReportData(SalesReportData data, boolean isSupervisor) {
        return !data.isConfidential() || isSupervisor;
    }

    protected SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
        // TODO Auto-generated method stub
        return null;
    }

}
