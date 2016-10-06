package myProject.service.Implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myProject.form.UserForm;
import myProject.service.UserService;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserFormValidator implements Validator{
	private final UserService serviceUser;
	public UserFormValidator(UserService serviceUser){
		this.serviceUser = serviceUser;
	}
	
	public boolean supports(Class<?> clazz) {
		return UserForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		UserForm form = (UserForm) target;
		
		if(form.getId()==0)if(serviceUser.findByLogin(form.getLogin())!=null){
			errors.rejectValue("login", "", "Username already exists");
		}
		Pattern p1 = Pattern.compile("^([a-zA-Z0-9]+([_\\.-]?[a-zA-Z0-9])*){5,15}$");
		Matcher m1 = p1.matcher(form.getLogin());
		if(!m1.matches()){
			errors.rejectValue("login", "", "Login format is ([a-zA-Z0-9]+([_\\.-]?[a-zA-Z0-9])*){5,15}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "", "Can`t be empty");
		
		if(form.getId()==0)if(serviceUser.findByPassword(form.getPassword())!=null){
			errors.rejectValue("password", "", "Password already exists");
		}
		Pattern p2 = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*[@./%*#$%^&+=])(?=\\S+$).{6,12}$");//(?=.*[@#$%^&+=])
		Matcher m2 = p2.matcher(form.getPassword());
		if(!m2.matches()){
			errors.rejectValue("password", "", "Password format is (?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,12}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Can`t be empty");
		
		if(form.getId()==0)if(serviceUser.findByFullName(form.getFullName())!=null){
			errors.rejectValue("fullName", "", "FullName already exists");
		}
		Pattern p3 = Pattern.compile("^([a-zA-Z0-9]+([_\\s-]?[a-zA-Z0-9])*){5,15}$");
		Matcher m3 = p3.matcher(form.getFullName());
		if(!m3.matches()){
			errors.rejectValue("fullName", "", "FullName format is [a-zA-Z0-9]+([_\\s-]?[a-zA-Z0-9])*{5,15}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "", "Can`t be empty");
		
		Pattern p4 = Pattern.compile("^[0-9]{1}$|^[1-4]{1}[0-9]{1}$|^50$");
		Matcher m4 = p4.matcher(form.getDrivingExperience());
		if(!m4.matches()){
			errors.rejectValue("drivingExperience", "", "DrivingExperience format is ^[0-9]{1}$|^[1-4]{1}[0-9]{1}$|^50");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "drivingExperience", "", "Can`t be empty");
		
		if(form.getId()==0)if(serviceUser.findByEmail(form.getEmail())!=null){
			errors.rejectValue("email", "", "Email already exists");
		}
		Pattern p5 = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
		Matcher m5 = p5.matcher(form.getEmail());
		if(!m5.matches()){
			errors.rejectValue("email", "", "Email format is [\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Can`t be empty");
	}
}
