package myProject.service.Implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myProject.form.CarForm;
import myProject.service.CarService;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CarFormValidator implements Validator{
	private final CarService serviceCar;
	public CarFormValidator(CarService serviceCar){
		this.serviceCar = serviceCar;
	}
	public boolean supports(Class<?> clazz) {
		return CarForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		CarForm form = (CarForm) target;
		if(form.getId()==0)if(serviceCar.findByRegistrationNamber(form.getRegistrationNamber())!=null){
			errors.rejectValue("registrationNamber", "", "RegistrationNamber already exists");
		}
		Pattern p = Pattern.compile("^[A-Z]{2}[0-9]{4}$");
		Matcher m = p.matcher(form.getRegistrationNamber());
		if(!m.matches()){
			errors.rejectValue("registrationNamber", "", "RegistrationNamber format is [A-Z]{2}[0-9]{4}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "registrationNamber", "", "RegistrationNamber can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modelOfCar", "", "ModelOfCar can`t be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "statusCar", "", "StatusCar can`t be empty");
	}

}
