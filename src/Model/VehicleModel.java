package Model;

import java.util.List;

public class VehicleModel {
	private int modelYear;
	private int odometer;
	private String model;
	private List<String> color = new List<>("Red", "Black", "White");
	private double priceDaily;
	private boolean isAvailable;
	
	
	
	public VehicleModel(int modelYear, int odometer, String model, List<String> color, double priceDaily, boolean isAvailable) {
		super();
		this.modelYear = modelYear;
		this.odometer = odometer;
		this.model = model;
		this.color = color;
		this.priceDaily = priceDaily;
		this.isAvailable = isAvailable;
	}
	public int getModelYear() {
		return modelYear;
	}
	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}
	public int getOdometer() {
		return odometer;
	}
	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public List<String> getColor() {
		return color;
	}
	public void setColor(List<String> color) {
		this.color = color;
	}
	public double getPriceDaily() {
		return priceDaily;
	}
	public void setPriceDaily(double priceDaily) {
		this.priceDaily = priceDaily;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	

}
