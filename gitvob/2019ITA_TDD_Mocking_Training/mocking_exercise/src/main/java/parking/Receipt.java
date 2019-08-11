package parking;

import java.util.Objects;

public class Receipt {

	private String carName = null;
	private String parkingLotName = null;
	
	public Receipt() {
		
	}
	
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	public String getCarName() {
		return this.carName;
	}

	public String getParkingLotName() {
		return parkingLotName;
	}

	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Receipt)) return false;
		Receipt receipt = (Receipt) o;
		return Objects.equals(getCarName(), receipt.getCarName()) &&
				Objects.equals(getParkingLotName(), receipt.getParkingLotName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCarName(), getParkingLotName());
	}
}
