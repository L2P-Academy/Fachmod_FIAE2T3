package controller;

import model.CarModel;
import model.MotorcycleModel;
import model.VehicleModel;

import java.util.List;

class RentController{
	XMLController controller = new XMLController();

	public void setRented(VehicleModel model) {
		model.setAvailable(false);
	}

	public void setAvailable(VehicleModel model){
		model.setAvailable(true);
	}
	public void createNewCar(int modelYear, int odometer, String model, List<String> color, double priceDaily,
							 boolean isAvailable, int doors, boolean hasAircon) {

		CarModel carModel = new CarModel(modelYear, odometer, model, color, priceDaily, isAvailable, doors, hasAircon);
		controller.writeVehicleListXML(carModel);
	}
	public void createNewMotorcycle(int modelYear, int odometer, String model, List<String> color, double priceDaily,
									boolean isAvailable, String licenseType, boolean hasTopCase) {

		MotorcycleModel motorcycleModel = new MotorcycleModel(modelYear, odometer, model, color, priceDaily, isAvailable,
				licenseType, hasTopCase);
		controller.writeVehicleListXML(motorcycleModel);
	}
	public void deleteCar(CarModel car) {
	}
	public void deleteMotorcycle(MotorcycleModel motorcycle) {
	}

	public void linkCustomerToVehicle() {

	}
	public void unlinkCustomerToVehicle() {

	}
}