package myProject.service.Implementation.editor;

import java.beans.PropertyEditorSupport;

import myProject.models.Custom;
import myProject.service.CustomService;

public class CustomEditor extends PropertyEditorSupport {
	private final CustomService serviceCustom;
	
	public CustomEditor(CustomService serviceCustom){
		this.serviceCustom = serviceCustom;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Custom custom = serviceCustom.findById(Integer.valueOf(text));
		setValue(custom);
	}
}
