package com.app.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.RoomUserDAO;
import com.app.model.RoomUser;
import com.app.utils.Constant;
@Repository
@Transactional(rollbackFor = Exception.class)
public class RoomUserDAOImpl  extends BaseDAOImpl<RoomUser> implements RoomUserDAO<RoomUser>{

	@Override
	public RoomUser findByName(String name) {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM RoomUser as model where model.activeFlag = 1 and model.name = :name ");
		query.setParameter("name", name);
		if(query.getSingleResult() == null) {
			return null;
		}else {
			return (RoomUser) query.getSingleResult();
		}
	}

	@Override
	public RoomUser findByRoomIdAndUserId(long roomId, long userId) {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM RoomUser as model where model.activeFlag = 1 and model.users.id = :userId and model.roomId =:roomId ");
		query.setParameter("roomId", roomId);
		query.setParameter("userId", userId);
		if(query.getResultList() == null || query.getResultList().isEmpty()) {
			return null;
		}else {
			return (RoomUser) query.getSingleResult();
		}
	}
	//select month(created_date) as month, sum(price) as price  from room_user where    year(created_date) = year(sysdate()) group by  month(created_date)
	@Override
	public List<Map<String, Object>> getBarChartByMonth(int year) {
		// TODO Auto-generated method stub
		String Sql = "SELECT MONTH(ru.created_Date) as month, sum(ru.price) as totalPrice FROM room_user ru "
				+ " WHERE year(ru.created_Date)  = ?  and ru.active_Flag = 1 "
				+ " group by month(ru.created_Date) ";
		org.hibernate.query.Query  query = factory.getCurrentSession().createNativeQuery(Sql);
		query.setParameter(1, year);
		List<Map<String , Object>> barCharts  =
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).list();

		return barCharts ;
	}

	@Override
	public List<Map<String, Object>> getCalendar(int roleId, long userId, int year , int month) {
		String sql= "";
		if (roleId == Constant.ROLE_STUDENT) {
			sql += "SELECT dl.date as start, dl.date as end , c.name as title FROM `room_user` ru ";
			sql += "inner join room r on r.id = ru.roomId ";
			sql += "inner join dateLearn dl on dl.roomId = r.id ";
			sql += "inner join Course c on c.id = r.courseId ";
			sql += "where r.active_flag = 1 and ru.active_flag = 1 and ru.user_id = ? ";
			sql += " and  year(dl.date) in (? , ?) and month(dl.date) in (? , ?)";
		}else if (roleId == Constant.ROLE_TEACHER) {
			sql += "SELECT dl.date as start, dl.date as end , c.name as title FROM `room` r ";
			sql += "inner join dateLearn dl on dl.roomId = r.id ";
			sql += "inner join Course c on c.id = r.courseId where r.active_flag = 1   and r.userId	=?	";
			sql += " and  year(dl.date) in (? , ?) and month(dl.date) in (? , ?)";
		}else {
			return null;
		}
		org.hibernate.query.Query  query = factory.getCurrentSession().createNativeQuery(sql);
		query.setParameter(1, userId);
		query.setParameter(2, year);
		query.setParameter(3, year + 1);
		query.setParameter(4,  month);
		query.setParameter(5,  month + 1);
		List<Map<String , Object>> calendars  =
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).list();
		return calendars;
	}


//	SELECT r.date as start, r.date as end , c.name as title FROM `room_user` ru
//	inner join room r on r.id = ru.roomId
//	inner join Course c on c.id = r.courseId
//	where r.active_flag = 1 and ru.active_flag = 1
//	and ru.user_id

}
