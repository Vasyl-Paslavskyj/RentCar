package myProject.service.Implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import myProject.form.CustomFormForUser;
import myProject.service.CustomService;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CustomFormForUserValidator implements Validator{
	private final CustomService serviceCustom;
	public CustomFormForUserValidator(CustomService serviceCustom) {
		this.serviceCustom = serviceCustom;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CustomFormForUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CustomFormForUser form = (CustomFormForUser) target;
		Pattern p1 = Pattern.compile("^(\\d{4}\\-\\d{1,2}\\-\\d{1,2}\\s\\d{1,2}\\:\\d{1,2})$");
		Matcher m1 = p1.matcher(form.getDateTimeStart());
		if(!m1.matches()){
			errors.rejectValue("dateTimeStart", "", "DateTimeStart format is (\\d{4}\\-\\d{1,2}\\-\\d{1,2}\\s\\d{1,2}\\:\\d{1,2})");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateTimeStart", "", "Can`t be empty");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cityStart", "", "Can`t be empty");
		
		if(form.getDateTimeStart().equals(form.getDateTimeFinish())){
			errors.rejectValue("dateTimeFinish", "", "dateTimeFinish cann't be equalse with dateTimeStart");
		}
		Pattern p2 = Pattern.compile("^(\\d{4}\\-\\d{1,2}\\-\\d{1,2}\\s\\d{1,2}\\:\\d{1,2})$");
		Matcher m2 = p2.matcher(form.getDateTimeFinish());
		if(!m2.matches()){
			errors.rejectValue("dateTimeFinish", "", "DateTimeFinish format is (\\d{4}\\-\\d{1,2}\\-\\d{1,2}\\s\\d{1,2}\\:\\d{1,2})");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateTimeFinish", "", "Can`t be empty");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cityFinish", "", "Can`t be empty");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "car", "", "Can`t be empty");
		
		Pattern p3 = Pattern.compile("^\\d{1,5}$");
		Matcher m3 = p3.matcher(form.getRentalLength());
		if(!m3.matches()){
			errors.rejectValue("rentalLength", "", "RentalLength format is \\d{1,5}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rentalLength", "", "Can`t be empty");
		
		Pattern p4 = Pattern.compile("^\\d{1,5}$");
		Matcher m4 = p4.matcher(form.getCost());
		if(!m4.matches()){
			errors.rejectValue("cost", "", "Cost format is \\d{1,5}");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "", "Can`t be empty");
	}
	

}
