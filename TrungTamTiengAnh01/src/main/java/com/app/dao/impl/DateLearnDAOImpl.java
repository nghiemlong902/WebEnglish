package com.app.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.DateLearnDAO;
import com.app.model.DateLearn;

@Repository
@Transactional(rollbackFor = Exception.class)
public class DateLearnDAOImpl  extends BaseDAOImpl<DateLearn>implements DateLearnDAO<DateLearn> {

	@Override
	public List<DateLearn> findByRoomId(long id) {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM DateLearn as model where model.roomId = :id ");
		query.setParameter("id", id);
		if(query.getResultList() == null) {
			return null;
		}else {
			return query.getResultList();
		}
	}

}
