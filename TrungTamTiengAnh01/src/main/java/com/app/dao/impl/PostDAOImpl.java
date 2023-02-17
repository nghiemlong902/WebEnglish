package com.app.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.PostDAO;
import com.app.model.Post;

@Repository
@Transactional(rollbackFor = Exception.class)
public class PostDAOImpl  extends BaseDAOImpl<Post>implements PostDAO<Post> {

	@Override
	public List<Post> getPostByLimitPosts(int limit) {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM Post as model where model.activeFlag = 1 order by model.id desc ");
		query.setFirstResult(0);
		query.setMaxResults(limit);
		if(query.getResultList() == null || query.getResultList().isEmpty()) {
			return null;
		}else {
			return query.getResultList();
		}
	}



}
