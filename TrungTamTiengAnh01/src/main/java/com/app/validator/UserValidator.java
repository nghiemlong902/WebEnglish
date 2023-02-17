package com.app.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.model.Users;
import com.app.service.UserService;

@Component
public class UserValidator  implements Validator{

	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(Users.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Users userDTO = (Users) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "error.required");
		if(userDTO.getId() == 0 ) {
			if(userDTO.getId() != 0) {
				if(!StringUtils.isEmpty(userDTO.getUsername())) {
					List<Users> users = userService.getAllByProperty("username", userDTO.getUsername());
					if(!users.isEmpty()) {
						errors.rejectValue("username", "error.exists");
					}
				}
			}
			if(!StringUtils.isEmpty(userDTO.getEmail())) {
				List<Users> users = userService.getAllByProperty("email", userDTO.getEmail());
				if(userDTO.getId() != 0) {
					if(!users.isEmpty()) {
						Users current = userService.findById(userDTO.getId());
						if(!userDTO.getEmail().equals(current.getEmail())) {
							errors.rejectValue("email", "error.exists");
						}
					}
				}else {
					if(!users.isEmpty()) {
						errors.rejectValue("email", "error.exists");
					}
				}
			}
			if(userDTO.getRoleId() == 0 ) {
				errors.rejectValue("roleId", "error.required");
			}

			if(userDTO.getGender() == 0 ) {
				errors.rejectValue("gender", "error.required");
			}
		}
	}
}
