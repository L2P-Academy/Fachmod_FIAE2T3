package model;

import java.util.ArrayList;
import java.util.List;

public class MotorcycleModel extends VehicleModel {
	private List<String> licenseType; 
	private boolean hasTopCase;
	

	public MotorcycleModel(int modelYear, int odometer, String model, List<String> color, double priceDaily,
			boolean isAvailable, int[] licenseKey, boolean hasTopCase) {
		
		super(modelYear, odometer, model, color, priceDaily, isAvailable);
		
		List<String> allLicenses = new ArrayList<>(List.of("A", "A1", "A2", "B196"));
		for(int licenses : licenseKey) {
			this.licenseType.add(allLicenses.get(licenses));	
		}
		
		
		this.hasTopCase = hasTopCase;
		
	}


	public List<String> getLicenseType() {
		return licenseType;
	}


	public void setLicenseType(List<String> licenseType) {
		this.licenseType = licenseType;
	}


	public boolean isHasTopCase() {
		return hasTopCase;
	}


	public void setHasTopCase(boolean hasTopCase) {
		this.hasTopCase = hasTopCase;
	}
	
	

}
