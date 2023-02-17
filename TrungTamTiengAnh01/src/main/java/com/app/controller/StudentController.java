package com.app.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dao.DateLearnDAO;
import com.app.model.Course;
import com.app.model.DateLearn;
import com.app.model.Room;
import com.app.model.RoomUser;
import com.app.model.Users;
import com.app.model.response.RoomResponse;
import com.app.model.response.RoomUserResponse;
import com.app.service.CourseService;
import com.app.service.RoomService;
import com.app.service.RoomUserService;
import com.app.service.UserService;
import com.app.utils.Constant;
import com.app.utils.Paging;
import com.app.validator.CourseValidator;

@Controller
@RequestMapping("/student-course")
public class StudentController {
	@Autowired
	CourseService courseService;
	@Autowired
	CourseValidator courseValidator;
	@Autowired
	RoomUserService roomUserService;
	@Autowired
	UserService userService;
	@Autowired
	RoomService roomService;
	@Autowired
	DateLearnDAO<DateLearn> dateLearnDAO;

	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		if(dataBinder.getTarget().getClass() == Course.class) {
			dataBinder.setValidator(courseValidator);
		}
	}
	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/student-course/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listCourse(Model model,@ModelAttribute("searchForm") Course course,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		Users users = (Users) session.getAttribute(Constant.USER_INFO);
		RoomUser roomUserFilter = new RoomUser();
		roomUserFilter.setUsers(new Users(users.getId()));
		List<RoomUser> list = roomUserService.getAll(roomUserFilter, paging);
		List<RoomUserResponse> roomUserResponses = null;
		if(list != null) {
			 roomUserResponses = new ArrayList<>(list.size());
			for(RoomUser element: list) {
				Room room = roomService.findById(element.getRoomId());
				Users  teacherUsers = userService.findById(room.getUserId());
				Course couse = courseService.findById(room.getCourseId());
				List<DateLearn> lstDates = dateLearnDAO.findByRoomId(element.getRoomId());
				RoomResponse roomResponse = new RoomResponse(room.getId(), room.getName(), teacherUsers.getId(),
						teacherUsers.getName(), couse.getId(), couse.getName(), lstDates);
				roomResponse.convert();
				roomUserResponses.add(new RoomUserResponse(element.getId(), element.getUsers(), element.getPoint(), roomResponse));
			}
		}

		model.addAttribute("list", roomUserResponses);
		model.addAttribute("pageInfo", paging);

		innitSelect(model);

		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		return "student-list";
	}

	private void innitSelect(Model model) {
		List<Room> lstRoom = roomService.getAll(null, null);
		List<RoomResponse> lstResponses = new ArrayList<>();

		for(Room element: lstRoom) {
			Course couse = courseService.findById(element.getCourseId());
			RoomResponse roomResponse = new RoomResponse();
			roomResponse.setCourseId(couse.getId());
			roomResponse.setCourseName(couse.getName());
			roomResponse.setId(element.getId());
			roomResponse.setName(element.getName());

			lstResponses.add(roomResponse);
		}
		model.addAttribute("lstRoom", lstResponses);
	}

	@PostMapping(value = {"/save"})
	public String save(Model model,@RequestParam("roomId") long roomId, HttpSession session) {
		Users users = (Users) session.getAttribute(Constant.USER_INFO);
		try {
			if(roomUserService.findByRoomIdAndUserId(roomId, users.getId()) == null) {
				Room room = roomService.findById(roomId);
				Course course = courseService.findById(room.getCourseId());
				RoomUser roomUser = new RoomUser();
				roomUser.setPrice(course.getPrice());
				roomUser.setPoint(new BigDecimal(0));
				roomUser.setRoomId(roomId);
				roomUser.setUsers(new Users(users.getId()));
				roomUserService.add(roomUser);
			}
			session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
		}

		return "redirect:/student-course/list/1";
	}
}
