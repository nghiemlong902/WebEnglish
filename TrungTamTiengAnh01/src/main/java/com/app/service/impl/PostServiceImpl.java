package com.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.PostDAO;
import com.app.model.Post;
import com.app.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostDAO<Post> postDAO;

	@Override
	public void add(Post post) throws Exception {
		// TODO Auto-generated method stub
		post.setActiveFlag(1);
		post.setCreatedDate(new Date());
		post.setUpdatedDate(new Date());
		postDAO.insert(post);
	}

	@Override
	public void delete(Post post) {
		// TODO Auto-generated method stub
		post.setActiveFlag(0);
		post.setUpdatedDate(new Date());
		postDAO.update(post);
		//courseDAO.delete(course);
	}


	@Override
	public Post findById(long id) {
		// TODO Auto-generated method stub
		Post post = postDAO.findById(Post.class, id);
		return post;
	}

	@Override
	public List<Post> getPostByLimitPosts(int limit){
		return postDAO.getPostByLimitPosts(limit);
	}


}
