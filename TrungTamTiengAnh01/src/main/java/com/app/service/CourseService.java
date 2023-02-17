package com.app.service;

import java.util.List;

import com.app.model.Course;
import com.app.utils.Paging;

public interface CourseService {
	void add(Course course) throws Exception;
	void delete(Course course) ;
	void update(Course course) throws Exception;
	List<Course> getAll(Course course , Paging paging);
	List<Course> getAllByProperty(String property , Object object);
	Course findById(long id);

	List<Course> getTopNew6Courses();
}
