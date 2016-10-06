package myProject.service.Implementation.editor;

import java.beans.PropertyEditorSupport;

import myProject.models.ModelOfCar;
import myProject.service.ModelOfCarService;

public class ModelOfCarEditor extends PropertyEditorSupport {
	private final ModelOfCarService serviceModelOfCar;
	
	public ModelOfCarEditor(ModelOfCarService serviceModelOfCar){
		this.serviceModelOfCar = serviceModelOfCar;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ModelOfCar modelOfCar = serviceModelOfCar.findById(Integer.valueOf(text));
		setValue(modelOfCar);
	}
}
