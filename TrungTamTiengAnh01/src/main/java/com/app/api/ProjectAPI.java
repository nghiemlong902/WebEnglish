package com.app.api;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Post;
import com.app.model.Users;
import com.app.service.PostService;
import com.app.service.RoomUserService;
import com.app.utils.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("http://localhost:8080")
public class ProjectAPI {
	@Autowired
	RoomUserService roomUserService;
	@Autowired
	PostService postService;

	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	  @GetMapping("/api/calendar")
	  public ResponseEntity<Object> addStudent(HttpSession session, @RequestParam("year") int year, @RequestParam("month") int month) {

		  ObjectMapper mapper = new ObjectMapper();
			Users users = (Users) session.getAttribute(Constant.USER_INFO);
			List<Map<String, Object>> calendars = roomUserService.getCalendar(users.getRoleId(), users.getId(), year, month);
			for(Map<String, Object> item: calendars) {
				item.put("start", dateTimeFormat.format(item.get("start")));
				item.put("end", dateTimeFormat.format(item.get("end")));
				//dateTimeFormat
			}

	    return new ResponseEntity<>(calendars, HttpStatus.OK);
	  }

	  @PostMapping("/api/post/create")
	  public ResponseEntity<Object> createPost(HttpSession session, @RequestBody Post postReq) {

		  try {
			Users users = (Users) session.getAttribute(Constant.USER_INFO);
			Post post = new Post();
			post.setContent(postReq.getContent());
			post.setTitle(postReq.getTitle());
			post.setUsers(users);
			postService.add(post);
			return new ResponseEntity<>("add success!! ", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return new ResponseEntity<>("add failed!!", HttpStatus.OK);
	  }

	  @GetMapping("/api/post/{id}")
	  public ResponseEntity<Object> getPost(HttpSession session, @PathVariable("id") long id) {
		  Post post = postService.findById(id);
		  return new ResponseEntity<>(post, HttpStatus.OK);
	  }

}
