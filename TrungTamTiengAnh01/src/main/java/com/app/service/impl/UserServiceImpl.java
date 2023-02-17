package com.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.dao.UserDAO;
import com.app.model.Users;
import com.app.service.UserService;
import com.app.utils.Paging;

@Service
public class UserServiceImpl  implements UserService{
	@Autowired
	UserDAO<Users> userDAO;


	@Override
	public void add(Users users) throws Exception {
		// TODO Auto-generated method stub
		users.setCreatedDate(new Date());
		users.setUpdatedDate(new Date());
		users.setActiveFlag(1);
		userDAO.insert(users);
	}

	@Override
	public void delete(Users users)  {
		// TODO Auto-generated method stub
		Users newUser = userDAO.findById(Users.class, users.getId());
		newUser.setActiveFlag(0);
		userDAO.delete(newUser);
	}

	@Override
	public void update(Users users) throws Exception {
		// TODO Auto-generated method stub
		users.setUpdatedDate(new Date());
		userDAO.update(users);
	}

	@Override
	public List<Users> getAll(Users userDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<>();
		StringBuilder queryStr = new StringBuilder();
		if(userDTO != null) {

			if(!StringUtils.isEmpty(userDTO.getName())) {
				queryStr.append(" and model.name like :name ");
				mapParams.put("name", "%"+userDTO.getName()+"%");
			}
			if(!StringUtils.isEmpty(userDTO.getUsername())) {
				queryStr.append(" and model.username like :username ");
				mapParams.put("username", "%"+userDTO.getUsername()+"%");
			}
			if(userDTO.getRoleId() > 0) {
				queryStr.append(" and model.roleId = :roleId ");
				mapParams.put("roleId", userDTO.getRoleId());
			}
		}

		return userDAO.findAll(mapParams, queryStr.toString(), paging);
	}

	@Override
	public List<Users> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		return userDAO.findByProperty(property, object);
	}

	@Override
	public Users findById(long id) {
		// TODO Auto-generated method stub
		Users user  = userDAO.findById(Users.class, id);
		return user;
	}

	@Override
	public long getTotalUser() {
		// TODO Auto-generated method stub
		return userDAO.getTotalUser();
	}

	@Override
	public Users findByUsername(String username) {
		// TODO Auto-generated method stub

		return  userDAO.findByUsername(username);
	}



}
