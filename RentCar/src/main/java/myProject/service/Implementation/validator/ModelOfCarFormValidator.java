package myProject.service.Implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myProject.form.ModelOfCarForm;
import myProject.service.ModelOfCarService;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ModelOfCarFormValidator implements Validator{
	private final ModelOfCarService serviceModelOfCar;
	public ModelOfCarFormValidator(ModelOfCarService serviceModelOfCar){
		this.serviceModelOfCar = serviceModelOfCar;
	}

	public boolean supports(Class<?> clazz) {
		return ModelOfCarForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ModelOfCarForm form = (ModelOfCarForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "classOfCar", "", "Can`t be empty");
		
		Pattern p1 = Pattern.compile("^([a-zA-Z0-9]+([_\\s-]+[a-zA-Z0-9])*){1,10}$");
		Matcher m1 = p1.matcher(form.getTypeModelCar());
		if(!m1.matches()){
			errors.rejectValue("typeModelCar", "", "TypeModelCar format is ([a-zA-Z0-9]+([_\\s-]+[a-zA-Z0-9])*){1,10}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeModelCar", "", "Can`t be empty");
	}

}
