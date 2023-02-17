package com.app.service;

import java.util.List;

import com.app.model.Room;
import com.app.utils.Paging;

public interface RoomService {
	void add(Room supplierDTO) throws Exception;
	void delete(Room room) ;
	void update(Room room) throws Exception;
	List<Room> getAll(Room room , Paging paging);
	List<Room> getAllByProperty(String property , Object object);
	Room findById(long id);

	public Room findByName(String name);

	Long save(Room room);
}
