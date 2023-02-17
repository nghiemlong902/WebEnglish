package com.app.dao;

import java.util.List;
import java.util.Map;

import com.app.model.RoomUser;

public interface RoomUserDAO<E> extends BaseDAO<E> {
	RoomUser  findByName(String name);

	RoomUser findByRoomIdAndUserId(long roomId, long userId);

	public List<Map<String, Object>> getBarChartByMonth(int year);

	public List<Map<String, Object>> getCalendar(int roleId, long userId, int year , int month);
}
