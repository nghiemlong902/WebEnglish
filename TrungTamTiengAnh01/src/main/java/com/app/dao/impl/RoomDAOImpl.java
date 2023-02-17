package com.app.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.RoomDAO;
import com.app.model.Room;
@Repository
@Transactional(rollbackFor = Exception.class)
public class RoomDAOImpl  extends BaseDAOImpl<Room> implements RoomDAO<Room>{

	@Override
	public Room findByName(String name) {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM Supplier as model where model.activeFlag = 1 and model.name = :name ");
		query.setParameter("name", name);
		if(query.getSingleResult() == null) {
			return null;
		}else {
			return (Room) query.getSingleResult();
		}
	}

	@Override
	public Long save(Room room) {
		// TODO Auto-generated method stub
		return (Long) factory.getCurrentSession().save(room);
	}

}
