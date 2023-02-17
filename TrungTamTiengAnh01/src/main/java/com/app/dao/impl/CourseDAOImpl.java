package com.app.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CourseDAO;
import com.app.model.Course;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CourseDAOImpl  extends BaseDAOImpl<Course>implements CourseDAO<Course> {

	@Override
	public Course findByName(String name) {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM course as model where model.activeFlag = 1 and model.name = :name ");
		query.setParameter("name", name);
		if(query.getSingleResult() == null) {
			return null;
		}else {
			return (Course) query.getSingleResult();
		}
	}

	@Override
	public List<Course> getTopNew6Courses() {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM Course as model where model.activeFlag = 1 order by model.id desc ");
		query.setFirstResult(0);
		query.setMaxResults(6);
		if(query.getResultList() == null || query.getResultList().isEmpty()) {
			return null;
		}else {
			return query.getResultList();
		}
	}

}
