package com.app.dao;

import java.util.List;

public interface PostDAO<Post> extends BaseDAO<Post>{
	List<Post> getPostByLimitPosts(int limit);
}
