package myProject.service.Implementation.editor;

import java.beans.PropertyEditorSupport;

import myProject.models.User;
import myProject.service.UserService;

public class UserEditor extends PropertyEditorSupport{
	private final UserService serviceUser;
	
	public UserEditor(UserService serviceUser){
		this.serviceUser = serviceUser;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		User user = serviceUser.findById(Integer.valueOf(text));
		setValue(user);
	}
}
