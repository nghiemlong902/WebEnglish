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
public class LoginValidator  implements Validator{
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.required");
		Users userDTO = (Users) target;
		if(!StringUtils.isEmpty(userDTO.getUsername()) && !StringUtils.isEmpty(userDTO.getPassword())) {
			//Users currentUser  = userService.findByUsername(userDTO.getUsername());
			List<Users> currentUsers = userService.getAllByProperty("username", userDTO.getUsername());
			if(!currentUsers.isEmpty()) {
				Users currentUser = currentUsers.get(0);
				if(! userDTO.getPassword().equals(currentUser.getPassword())) {
					errors.rejectValue("password", "error.login");
				}
			}else {
				errors.rejectValue("password", "error.login");
			}
		}
	}

}
