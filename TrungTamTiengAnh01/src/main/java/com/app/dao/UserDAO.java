package com.app.dao;

import com.app.model.Users;

public interface UserDAO<E> extends BaseDAO<E> {
	long getTotalUser();

	Users findByUsername(String username);
}
