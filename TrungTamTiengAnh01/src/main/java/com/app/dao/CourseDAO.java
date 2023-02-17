package com.app.dao;

import java.util.List;

import com.app.model.Course;

public interface CourseDAO<E> extends BaseDAO<E> {
	Course findByName(String name);

	List<Course> getTopNew6Courses();
}
