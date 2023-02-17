package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.Post;
import com.app.model.response.PostResponse;
import com.app.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {

	@Autowired
	private PostService postService;

	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy");

	@GetMapping
	public String listPost(Model model) {
		List<Post> listPosts = postService.getPostByLimitPosts(10);
		List<PostResponse> listResponses = null;
		if(listPosts != null) {
			listResponses = new ArrayList<>(10);
			for(Post post: listPosts) {
				PostResponse response = new PostResponse();
				response.setAuthor(post.getUsers().getName());
				response.setContent(post.getContent());
				response.setDate(dateTimeFormat.format(post.getCreatedDate()));
				response.setId(post.getId());
				response.setTitle(post.getTitle());
				response.subStr();
				listResponses.add(response);
			}
		}

		model.addAttribute("listPosts", listResponses);
		return "post-list";
	}
}
