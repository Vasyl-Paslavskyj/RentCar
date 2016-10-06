package myProject.service.Implementation.editor;

import java.beans.PropertyEditorSupport;

import myProject.models.StatusCar;
import myProject.service.StatusCarService;

public class StatusCarEditor extends PropertyEditorSupport{
	private final StatusCarService serviceStatusCar;
	
	public StatusCarEditor(StatusCarService serviceStatusCar){
		this.serviceStatusCar = serviceStatusCar;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		StatusCar statusCar = serviceStatusCar.findById(Integer.valueOf(text));
		setValue(statusCar);
	}
}
