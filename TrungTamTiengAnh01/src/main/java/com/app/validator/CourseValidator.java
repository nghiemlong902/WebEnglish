package com.app.validator;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.model.Course;
import com.app.service.CourseService;
@Component
public class CourseValidator implements Validator {

	@Autowired
	CourseService courseService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(Course.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Course course = (Course) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.required");

		if(course != null) {
			if(!StringUtils.isEmpty(course.getName())) {
				List<Course> courses = courseService.getAllByProperty("name", course.getName());
				if(course.getId() != 0) {
					if(!courses.isEmpty()) {
						Course current = courseService.findById(course.getId());
						if(!course.getName().equals(current.getName())) {
							errors.rejectValue("name", "error.exists");
						}
					}
				}else {
					if(!courses.isEmpty()) {
						errors.rejectValue("name", "error.exists");
					}
				}
			}

			if(course.getPrice()!= null && course.getPrice().compareTo(new BigDecimal(0)) < 0 ) {
				errors.rejectValue("price", "error.failed ");
			}

			if(course.getId() == 0) {
				if(course.getImgFile() != null && course.getImgFile().isEmpty()) {
					errors.rejectValue("imgFile", "error.required");
				}
			}
		}

	}

}
