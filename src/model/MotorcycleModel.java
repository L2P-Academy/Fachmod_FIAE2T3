package model;

import java.util.ArrayList;
import java.util.List;

public class MotorcycleModel extends VehicleModel {
	private String licenseType;
	private boolean hasTopCase;
	private List<String> allLicenses = new ArrayList<>(List.of("A1", "B196", "A2", "A"));

	public MotorcycleModel(int modelYear, int odometer, String model, List<String> color, double priceDaily,
			boolean isAvailable, String licenseType, boolean hasTopCase) {
		
		super(modelYear, odometer, model, color, priceDaily, isAvailable);
		this.licenseType = licenseType;
		this.hasTopCase = hasTopCase;
		
	}


	public String getLicenseType() {
		return licenseType;
	}


	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}


	public boolean getHasTopCase() {
		return hasTopCase;
	}


	public void setHasTopCase(boolean hasTopCase) {
		this.hasTopCase = hasTopCase;
	}
	
	

}
