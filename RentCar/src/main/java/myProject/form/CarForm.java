package myProject.form;

import myProject.models.ModelOfCar;
import myProject.models.StatusCar;

public class CarForm {
	private long id;
	
	private ModelOfCar modelOfCar;
	
	private StatusCar statusCar;
	
	private String registrationNamber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ModelOfCar getModelOfCar() {
		return modelOfCar;
	}

	public void setModelOfCar(ModelOfCar modelOfCar) {
		this.modelOfCar = modelOfCar;
	}

	public StatusCar getStatusCar() {
		return statusCar;
	}

	public void setStatusCar(StatusCar statusCar) {
		this.statusCar = statusCar;
	}

	public String getRegistrationNamber() {
		return registrationNamber;
	}

	public void setRegistrationNamber(String registrationNamber) {
		this.registrationNamber = registrationNamber;
	}
	
}
