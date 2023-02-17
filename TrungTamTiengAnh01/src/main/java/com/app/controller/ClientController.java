package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.Course;
import com.app.model.OrderCourse;
import com.app.service.CourseService;
import com.app.service.OrderService;
import com.app.utils.Constant;
import com.app.utils.Paging;


@Controller
public class ClientController {

	@Autowired
	CourseService courseService;
	@Autowired
	private OrderService orderService;




	@GetMapping(value = {"/trang-chu"})
	public String index(ModelMap map, HttpSession session, Model model) {
		List<Course> courses = 	courseService.getTopNew6Courses();
		map.addAttribute("courses", courses);
		map.addAttribute("searchForm", new Course());
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		return "trang-chu";
	}

	@RequestMapping(value = {"/danh-sach-khoa-hoc"})
	public String redirectListCourse(ModelMap map) {
		return "redirect:/danh-sach-khoa-hoc/1";
	}

	@RequestMapping(value = {"/danh-sach-khoa-hoc/{page}"})
	public String listCourse(ModelMap map, @PathVariable("page") int page, @ModelAttribute("searchForm") Course course) {
		Paging paging = new Paging(9);
		paging.setIndexPage(page);
		List<Course> courses = 	courseService.getAll(course, paging);
		map.addAttribute("courses", courses);
		map.addAttribute("pageInfo", paging);
		return "khoa-hoc";
	}

	@GetMapping(value = {"/dat-hang/{id}"})
	public String index(ModelMap map, @PathVariable("id") long id) {
		Course course = courseService.findById(id);
		map.addAttribute("course", course);
		map.addAttribute("searchForm", new Course());
		map.addAttribute("submitForm", new OrderCourse());
		return "dat-hang";
	}





}
