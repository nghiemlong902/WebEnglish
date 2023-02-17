package com.app.dao;

import java.util.List;

public interface DateLearnDAO<DateLearn> extends BaseDAO<DateLearn>{
	List<DateLearn> findByRoomId(long roomId);
}
