package com.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.OrderDAO;
import com.app.model.OrderCourse;
import com.app.model.request.OrderSearchRequest;
import com.app.service.OrderService;
import com.app.utils.Paging;

@Service
public class OrderServiceImpl  implements OrderService{
	@Autowired
	OrderDAO<OrderCourse> orderDAO;

	@Autowired
	ServletContext context;

	@Override
	public void add(OrderCourse orderCourse) throws Exception {
		// TODO Auto-generated method stub
		orderCourse.setCreatedDate(new Date());
		orderCourse.setUpdatedDate(new Date());
		orderCourse.setActiveFlag(1);
		orderDAO.insert(orderCourse);
	}

	@Override
	public void delete(OrderCourse orderCourse) {
		// TODO Auto-generated method stub
		orderCourse.setActiveFlag(1);
		orderDAO.update(orderCourse);
	}

	@Override
	public void update(OrderCourse orderCourse) throws Exception {
		// TODO Auto-generated method stub
		orderCourse.setUpdatedDate(new Date());
		orderDAO.update(orderCourse);
	}

	@Override
	public List<OrderCourse> getAll(OrderSearchRequest orderSearchRequest, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<>();
		StringBuilder queryStr = new StringBuilder();
		if(orderSearchRequest != null) {

			if(orderSearchRequest.getDateFrom() != null && orderSearchRequest.getDateto() != null) {
				queryStr.append(" and date(model.createdDate ) between :dateFrom and :dateTo ");
				mapParams.put("dateTo", orderSearchRequest.getDateto());
				mapParams.put("dateFrom", orderSearchRequest.getDateFrom());
			}
		}

		return orderDAO.findAll(mapParams, queryStr.toString(), paging);
	}

	@Override
	public List<OrderCourse> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		return orderDAO.findByProperty(property, object);
	}

	@Override
	public OrderCourse findById(long id) {
		// TODO Auto-generated method stub
		return orderDAO.findById(OrderCourse.class, id);
	}


}
