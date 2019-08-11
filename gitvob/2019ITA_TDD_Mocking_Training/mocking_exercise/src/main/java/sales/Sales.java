package sales;

import java.util.Date;

public class Sales {
	private String salesId;
	private boolean isSupervisor;
	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public Date getEffectiveTo() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getEffectiveFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSupervisor() {
		return isSupervisor;
	}

	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}
}
