package myProject.service.Implementation.editor;

import java.beans.PropertyEditorSupport;

import myProject.models.Accident;
import myProject.service.AccidentService;

public class AccidentEditor extends PropertyEditorSupport {
	private final AccidentService serviceAccident;

	public AccidentEditor(AccidentService serviceAccident) {
		this.serviceAccident = serviceAccident;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Accident accident = serviceAccident.findById(Integer.valueOf(text));
		setValue(accident);
	}
}
