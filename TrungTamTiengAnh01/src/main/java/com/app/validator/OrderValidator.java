package com.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.model.OrderCourse;
@Component
public class OrderValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(OrderCourse.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.required");


	}

}
