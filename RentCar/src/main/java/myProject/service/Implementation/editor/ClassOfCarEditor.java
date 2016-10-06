package myProject.service.Implementation.editor;

import java.beans.PropertyEditorSupport;

import myProject.models.ClassOfCar;
import myProject.service.ClassOfCarService;

public class ClassOfCarEditor extends PropertyEditorSupport {
	
	private final ClassOfCarService serviceClassOfCar;
	
	public ClassOfCarEditor(ClassOfCarService serviceClassOfCar){
		this.serviceClassOfCar = serviceClassOfCar;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ClassOfCar classOfCar = serviceClassOfCar.findById(Integer.valueOf(text));
		setValue(classOfCar);
	}
	
}
