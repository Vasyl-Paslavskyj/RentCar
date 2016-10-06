package myProject.service.Implementation.editor;

import java.beans.PropertyEditorSupport;

import myProject.models.Car;
import myProject.service.CarService;

public class CarEditor extends PropertyEditorSupport{
	private final CarService serviceCar;
	
	public CarEditor(CarService serviceCar){
		this.serviceCar = serviceCar;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Car car = serviceCar.findById(Integer.valueOf(text));
		setValue(car);
	}
}
