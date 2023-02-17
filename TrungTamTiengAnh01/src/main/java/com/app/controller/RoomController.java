package com.app.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.app.service.CourseService;
import com.app.service.RoomService;
import com.app.service.RoomUserService;
import com.app.service.UserService;
import com.app.utils.Constant;
import com.app.utils.Paging;
import com.app.validator.RoomValidator;

@Controller
@RequestMapping("/room")
public class RoomController {
	@Autowired
	RoomService roomService;
	@Autowired
	CourseService courseService;
	@Autowired
	UserService userService;
	@Autowired
	RoomUserService roomUserService;
	@Autowired
	DateLearnDAO<DateLearn> dateLearnDAO;


	@Autowired
	RoomValidator roomValidator;
	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		if(dataBinder.getTarget().getClass() == Room.class) {
			dataBinder.setValidator(roomValidator);
		}
	}
	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/room/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listRoom(Model model,@ModelAttribute("searchForm") Room room, @PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		Users users = (Users) session.getAttribute(Constant.USER_INFO);
		if(users != null && users.getRoleId() == Constant.ROLE_TEACHER) {
			room.setUserId(users.getId());
		}

		List<Room> list = roomService.getAll(room, paging);
		List<RoomResponse> roomResponses = null;
		if(list != null) {
			roomResponses = new ArrayList<>(list.size());
			for(Room element: list) {
				//long id, String name, long techerId, long techerName, long courseId, String courseName, date
				Users teacher = userService.findById(element.getUserId());
				Course course = courseService.findById(element.getCourseId());
				List<DateLearn> lstDates = dateLearnDAO.findByRoomId(element.getId());
				RoomResponse roomResponse = new RoomResponse(element.getId(), element.getName(), teacher.getId(), teacher.getName(), course.getId(), course.getName(), lstDates);
				roomResponse.convert();
				roomResponses.add(roomResponse);
			}
		}

		model.addAttribute("list", roomResponses);
		model.addAttribute("pageInfo", paging);
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		return "room-list";
	}
	@GetMapping(value = {"/add"})
	public String addRoom(Model model) {
		model.addAttribute("title", "Add");
		model.addAttribute("submitForm", new Room());
		model.addAttribute("viewOnly", false);
		innitSelect(model);
		return "room-action";
	}
//	@GetMapping(value = {"/view/{id}"})
//	public String viewRoom(Model model,@PathVariable("id") long id) {
//		Room roomDTO = roomService.findById(id);
//		roomDTO.setDateStr(dateTimeFormat.format(roomDTO.getDate()));
//		model.addAttribute("title", "View");
//		model.addAttribute("submitForm", roomDTO);
//		model.addAttribute("viewOnly", true);
//		innitSelect(model);
//		return "room-action";
//	}
	@GetMapping(value = {"/edit/{id}"})
	public String editRoom(Model model,@PathVariable("id") int id) {
		Room roomDTO = roomService.findById(id);

		model.addAttribute("title", "Edit");
		model.addAttribute("submitForm", roomDTO);
		model.addAttribute("viewOnly", false);
		innitSelect(model);
		return "room-action";
	}
	@PostMapping(value = {"/save"})
	public String saveRoom(Model model,@ModelAttribute("submitForm") @Validated Room roomDTO
			, BindingResult result, HttpSession session ) {
		if(result.hasErrors()) {
			if(roomDTO.getId() != 0) {
				model.addAttribute("title", "Edit");
			}else {
				model.addAttribute("title", "Add");
			}
			model.addAttribute("submitForm", roomDTO);
			innitSelect(model);
			return "room-action";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		if(roomDTO.getId() != 0) {
			try {

				roomService.update(roomDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				roomDTO.setActiveFlag(1);
				roomDTO.setCreatedDate(new Date());
				roomDTO.setUpdatedDate(new Date());
				String[] arr = roomDTO.getDateStr().split(",");

				long id = roomService.save(roomDTO);
				for(String item: arr) {
					Date date = sdf.parse(item);
					DateLearn dateLearn= new DateLearn(date);
					dateLearn.setRoomId(id);
					dateLearnDAO.insert(dateLearn);
				}
//				roomDTO.setLstDateLearns(lstDates);
//				roomService.add(roomDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		return "redirect:/room/list/1";
	}
	@GetMapping(value = {"/delete/{id}"})
	public String deleteRoom(Model model,@PathVariable("id")int id,HttpSession session) {
		Room roomDTO = roomService.findById(id);
		try {
			roomService.delete(roomDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}

		return "redirect:/room/list/1";
	}

	private void innitSelect(Model model) {
		List<Course> courses = courseService.getAll(null, null);
		Collections.sort(courses,new Comparator<Course>() {
			@Override
			public int compare(Course o1, Course o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
		});
		model.addAttribute("listCourse", courses);

		Users userFilter = new Users();
		userFilter.setRoleId(Constant.ROLE_TEACHER);
		List<Users> users = userService.getAll(userFilter, null);
		Collections.sort(users,new Comparator<Users>() {
			@Override
			public int compare(Users o1, Users o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
		});
		model.addAttribute("listUser", users);
	}



	@RequestMapping("/{id}/student/list/{page}")
	public String listRoom(Model model, @PathVariable("id") long id, @PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		RoomUser roomUserFilter = new RoomUser();
		roomUserFilter.setRoomId(id);
		List<RoomUser> list = roomUserService.getAll(roomUserFilter, paging);

		Room room = roomService.findById(id);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
		model.addAttribute("room", room);

		Users userFilter = new Users();
		userFilter.setRoleId(Constant.ROLE_STUDENT);
		List<Users> users = userService.getAll(userFilter, null);
		Collections.sort(users,new Comparator<Users>() {
			@Override
			public int compare(Users o1, Users o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
		});
		model.addAttribute("listUser", users);

		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		return "room-student-list";
	}

	@PostMapping(value = {"/{id}/student/save"})
	public String addStudent(Model model,@PathVariable("id")int id,HttpSession session, @RequestParam("userId") long userId) {

		try {
			if(roomUserService.findByRoomIdAndUserId(id, userId) == null) {
				Room room = roomService.findById(id);
				Course course = courseService.findById(room.getCourseId());
				RoomUser roomUser = new RoomUser();
				roomUser.setPrice(course.getPrice());
				roomUser.setPoint(new BigDecimal(0));
				roomUser.setRoomId(id);
				roomUser.setUsers(new Users(userId));
				roomUserService.add(roomUser);
			}

			session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
		}
		String uriRedirect="redirect:/room/"+id+"/student/list/1";
		return uriRedirect;
	}

	@PostMapping(value = {"/{roomId}/student/{studentId}/add-point"})
	public String addStudent(Model model, HttpSession session, @PathVariable("studentId") long userId, @PathVariable("roomId") long roomId,
			@RequestParam("point") long point) {

		try {
			RoomUser roomUser = roomUserService.findByRoomIdAndUserId(roomId, userId);
			roomUser.setPoint(new BigDecimal(point));
			roomUserService.update(roomUser);
			session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
		}
		String uriRedirect="redirect:/room/"+roomId+"/student/list/1";
		return uriRedirect;
	}

	@GetMapping(value = {"/{roomId}/student/delete/{id}"})
	public String addStudent(Model model, HttpSession session,  @PathVariable("roomId") long roomId, @PathVariable("id") long id) {

		try {
			RoomUser roomUser = roomUserService.findById(id);
			roomUserService.delete(roomUser);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
		String uriRedirect="redirect:/room/"+roomId+"/student/list/1";
		return uriRedirect;
	}
}
