package com.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.dao.RoomDAO;
import com.app.model.Room;
import com.app.service.RoomService;
import com.app.utils.Paging;

@Service
public class RoomServiceImpl  implements RoomService{
	@Autowired
	RoomDAO<Room> roomDAO;

	@Override
	public void add(Room room) throws Exception {
		// TODO Auto-generated method stub
		room.setActiveFlag(1);
		room.setUpdatedDate(new Date());
		room.setCreatedDate(new Date());
		roomDAO.insert(room);
	}

	@Override
	public void delete(Room room) {
		// TODO Auto-generated method stub
		room.setActiveFlag(0);
		roomDAO.delete(room);
	}

	@Override
	public void update(Room room) throws Exception {
		// TODO Auto-generated method stub

		roomDAO.update(room);
	}

	@Override
	public List<Room> getAll(Room room, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<>();
		StringBuilder queryStr = new StringBuilder();
		if(room != null) {

			if(!StringUtils.isEmpty(room.getName())) {
				queryStr.append(" and model.name like :name ");
				mapParams.put("name", "%"+room.getName()+"%");
			}

			if(room.getUserId() > 0) {
				queryStr.append(" and model.userId = :userId ");
				mapParams.put("userId", room.getUserId());
			}
		}

		return roomDAO.findAll(mapParams, queryStr.toString(), paging);
	}

	@Override
	public List<Room> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		return roomDAO.findByProperty(property, object);
	}

	@Override
	public Room findById(long id) {
		// TODO Auto-generated method stub
		return roomDAO.findById(Room.class, id);
	}

	@Override
	public Room findByName(String name) {
		return roomDAO.findByName(name);
	}

	@Override
	public Long save(Room room) {
		// TODO Auto-generated method stub
		return roomDAO.save(room);
	}

}
