package com.app.dao;

import com.app.model.Room;

public interface RoomDAO<E> extends BaseDAO<E> {
	Room  findByName(String name);

	Long save(Room room);
}
