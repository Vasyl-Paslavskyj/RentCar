package myProject.service.Implementation.editor;

import java.beans.PropertyEditorSupport;

import myProject.models.Status;

public class StatusEditor extends PropertyEditorSupport {

	public StatusEditor() {
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Status status = Status.valueOf(text.toUpperCase());
		setValue(status);
	}
}
