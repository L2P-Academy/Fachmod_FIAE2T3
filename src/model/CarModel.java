package model;

import java.util.List;

public class CarModel extends VehicleModel{
	private int doors;
	private boolean hasAircon;
	
	
	public CarModel(int modelYear, int odometer, String model, List<String> color, double priceDaily,
			boolean isAvailable, int doors, boolean hasAircon) {
		super(modelYear, odometer, model, color, priceDaily, isAvailable);
		this.doors = doors;
		this.hasAircon = hasAircon;
	}


	public int getDoors() {
		return doors;
	}


	public void setDoors(int doors) {
		this.doors = doors;
	}


	public boolean getHasAircon() {
		return hasAircon;
	}


	public void setAircon(boolean hasAircon) {
		this.hasAircon = hasAircon;
	}
	
	

}
