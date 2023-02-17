package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.model.RoomUser;
import com.app.utils.Paging;

public interface RoomUserService {
	void add(RoomUser roomUser) throws Exception;
	void delete(RoomUser roomUser) ;
	void update(RoomUser roomUser) throws Exception;
	List<RoomUser> getAll(RoomUser roomUser , Paging paging);
	List<RoomUser> getAllByProperty(String property , Object object);
	RoomUser findById(long id);

	RoomUser findByRoomIdAndUserId(long roomId, long userId);

	List<Map<String, Object>> getBarChartByMonth(int year);

	  List<Map<String, Object>> getCalendar(int roleId, long userId, int year , int month);

}
