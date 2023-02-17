package com.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.model.Room;
import com.app.service.CourseService;
@Component
public class RoomValidator implements Validator {

	@Autowired
	CourseService courseService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(Room.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Room room = (Room) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateStr", "error.required");
//
		if(room != null) {
			if(room.getCourseId() == 0) {
				errors.rejectValue("courseId", "error.required");
			}

			if(room.getUserId() == 0) {
				errors.rejectValue("userId", "error.required");
			}
		}

	}

}
