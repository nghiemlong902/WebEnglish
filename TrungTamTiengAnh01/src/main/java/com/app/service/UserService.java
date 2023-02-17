package com.app.service;

import java.util.List;

import com.app.model.Users;
import com.app.utils.Paging;

public interface UserService {

	void add(Users users) throws Exception;
	void delete(Users users) ;
	void update(Users users) throws Exception;
	List<Users> getAll(Users users , Paging paging);
	List<Users> getAllByProperty(String property , Object object);
	Users findById(long id);
	Users findByUsername(String username);
	public long getTotalUser();

}
