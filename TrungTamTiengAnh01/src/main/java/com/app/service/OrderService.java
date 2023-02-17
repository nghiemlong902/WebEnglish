package com.app.service;

import java.util.List;

import com.app.model.OrderCourse;
import com.app.model.request.OrderSearchRequest;
import com.app.utils.Paging;

public interface OrderService {
	void add(OrderCourse orderCourse) throws Exception;
	void delete(OrderCourse orderCourse) ;
	void update(OrderCourse orderCourse) throws Exception;
	List<OrderCourse> getAll(OrderSearchRequest OrderSearchRequest , Paging paging);
	List<OrderCourse> getAllByProperty(String property , Object object);
	OrderCourse findById(long id);


}
