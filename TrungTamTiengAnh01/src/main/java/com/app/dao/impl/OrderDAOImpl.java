package com.app.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.OrderDAO;
import com.app.model.OrderCourse;

@Repository
@Transactional(rollbackFor = Exception.class)
public class OrderDAOImpl  extends BaseDAOImpl<OrderCourse>implements OrderDAO<OrderCourse> {


}
