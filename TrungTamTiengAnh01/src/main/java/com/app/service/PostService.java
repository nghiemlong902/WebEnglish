package com.app.service;

import java.util.List;

import com.app.model.Post;

public interface PostService {
	void add(Post post) throws Exception;
	void delete(Post post) ;
	Post findById(long id);

	List<Post> getPostByLimitPosts(int limit);

}
