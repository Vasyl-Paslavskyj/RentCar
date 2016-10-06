package myProject.service.Implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myProject.form.AccidentForm;
import myProject.service.AccidentService;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AccidentFormValidator implements Validator{
	private final AccidentService serviceAccident;
	public AccidentFormValidator(AccidentService serviceAccident){
		this.serviceAccident = serviceAccident;
	}

	public boolean supports(Class<?> clazz) {
		return AccidentForm.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		AccidentForm form = (AccidentForm) target;
		
		Pattern p1 = Pattern.compile("^(\\d{4}\\-\\d{1,2}\\-\\d{1,2}\\s\\d{1,2}\\:\\d{1,2})$");
		Matcher m1 = p1.matcher(form.getDateTime());
		if(!m1.matches()){
			errors.rejectValue("dateTime", "", "DateTime format is ([2016-2200]{4}\\-[1-12]{1,2}\\-[1,31]{1,2}\\s[0-24]{1,2}\\:[0-60]{1,2})");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateTime", "", "Can`t be empty");
		
		Pattern p2 = Pattern.compile("^([a-zA-Z0-9]+([_\\s-]+[a-zA-Z0-9])*([\\!\\?])?){1,30}$");
		Matcher m2 = p2.matcher(form.getDamage());
		if(!m2.matches()){
			errors.rejectValue("damage", "", "Damage format is ([a-zA-Z0-9]+([_\\s-]+[a-zA-Z0-9])*([\\!\\?])?){1,30}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "damage", "", "Can`t be empty");
		
		Pattern p3 = Pattern.compile("^\\d{1,5}$");
		Matcher m3 = p3.matcher(form.getWastage());
		if(!m3.matches()){
			errors.rejectValue("wastage", "", "Wastage format is \\d{1,5}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "wastage", "", "Can`t be empty");
		
		if(form.getCustomSet().isEmpty())
			errors.rejectValue( "customSet", "", "Can`t be empty");
	}
}
