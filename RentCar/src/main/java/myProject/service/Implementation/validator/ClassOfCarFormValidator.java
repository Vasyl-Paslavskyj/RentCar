package myProject.service.Implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myProject.form.ClassOfCarForm;
import myProject.service.ClassOfCarService;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ClassOfCarFormValidator implements Validator{
	private final ClassOfCarService serviceClassOfCar;

	public ClassOfCarFormValidator(ClassOfCarService serviceClassOfCar) {
		this.serviceClassOfCar = serviceClassOfCar;
	}

	public boolean supports(Class<?> clazz) {
		return ClassOfCarForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ClassOfCarForm form = (ClassOfCarForm) target;
		
		Pattern p = Pattern.compile("^[0-9]{1,4}$");
		Matcher m = p.matcher(form.getPrice());
		if(!m.matches()){
			errors.rejectValue("price", "", "Price form is [0-9]{1,4}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "", "Can`t be empty");
		
		if(form.getId()==0)if(serviceClassOfCar.findByTypeClassOfCar(form.getTypeClassOfCar())!=null){
			errors.rejectValue("typeClassOfCar", "", "TypeClassOfCar already exists");
		}
		Pattern p1 = Pattern.compile("^\\w{3,10}$");
		Matcher m1 = p1.matcher(form.getTypeClassOfCar());
		if(!m1.matches()){
			errors.rejectValue("typeClassOfCar", "", "TypeClassOfCar form is \\w{3,10}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeClassOfCar", "", "Can`t be empty");
	}

}
