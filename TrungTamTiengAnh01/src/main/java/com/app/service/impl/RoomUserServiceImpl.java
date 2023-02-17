package com.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.dao.RoomUserDAO;
import com.app.model.RoomUser;
import com.app.service.RoomUserService;
import com.app.utils.Paging;

@Service
public class RoomUserServiceImpl  implements RoomUserService{
	@Autowired
	RoomUserDAO<RoomUser> roomDAO;

	@Override
	public void add(RoomUser room) throws Exception {
		// TODO Auto-generated method stub
		room.setActiveFlag(1);
		room.setUpdatedDate(new Date());
		room.setCreatedDate(new Date());
		roomDAO.insert(room);
	}

	@Override
	public void delete(RoomUser room) {
		// TODO Auto-generated method stub
		room.setActiveFlag(0);
		roomDAO.delete(room);
	}

	@Override
	public void update(RoomUser room) throws Exception {
		// TODO Auto-generated method stub
		roomDAO.update(room);
	}

	@Override
	public List<RoomUser> getAll(RoomUser room, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<>();
		StringBuilder queryStr = new StringBuilder();
		if(room != null) {
			if(room.getUsers() != null && !StringUtils.isEmpty(room.getUsers().getName())) {
				queryStr.append(" and model.users.name like :name ");
				mapParams.put("name", "%"+room.getUsers().getName()+"%");
			}
			if(room.getRoomId() > 0) {
				queryStr.append(" and model.roomId = :roomId ");
				mapParams.put("roomId", room.getRoomId());
			}
			if(room.getUsers() != null ) {
				queryStr.append(" and model.users.id = :userId ");
				mapParams.put("userId", room.getUsers().getId());
			}
		}

		return roomDAO.findAll(mapParams, queryStr.toString(), paging);
	}

	@Override
	public List<RoomUser> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		return roomDAO.findByProperty(property, object);
	}

	@Override
	public RoomUser findById(long id) {
		// TODO Auto-generated method stub
		return roomDAO.findById(RoomUser.class, id);
	}

	@Override
	public RoomUser findByRoomIdAndUserId(long roomId, long userId) {
		// TODO Auto-generated method stub
		return roomDAO.findByRoomIdAndUserId(roomId, userId);
	}

	@Override
	public List<Map<String, Object>> getBarChartByMonth(int year) {
		// TODO Auto-generated method stub
		return roomDAO.getBarChartByMonth(year);
	}

	@Override
	public List<Map<String, Object>> getCalendar(int roleId, long userId, int year , int month) {
		// TODO Auto-generated method stub
		return roomDAO.getCalendar(roleId, userId, year, month);
	}


}
