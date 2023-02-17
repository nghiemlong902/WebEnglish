package com.app.controller;

import java.text.SimpleDateFormat;
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

import com.app.model.Course;
import com.app.service.CourseService;
import com.app.utils.Constant;
import com.app.utils.Paging;
import com.app.validator.CourseValidator;

@Controller
@RequestMapping("/course")
public class CourseController {
	@Autowired
	CourseService courseService;
	@Autowired
	CourseValidator courseValidator;
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
		return "redirect:/course/list/1";
	}
	@RequestMapping("/list/{page}")
	public String listCourse(Model model,@ModelAttribute("searchForm") Course course,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		List<Course> list = courseService.getAll(course, paging);
		model.addAttribute("list", list);
		model.addAttribute("pageInfo", paging);
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		return "course-list";
	}
	@GetMapping(value = {"/add"})
	public String addCourse(Model model) {
		model.addAttribute("title", "Add");
		model.addAttribute("submitForm", new Course());
		model.addAttribute("viewOnly", false);
		return "course-action";
	}
	@GetMapping(value = {"/view/{id}"})
	public String viewCourse(Model model,@PathVariable("id") int id) {
		Course course = courseService.findById(id);
		model.addAttribute("title", "View");
		model.addAttribute("submitForm", course);
		model.addAttribute("viewOnly", true);
		return "course-action";
	}
	@GetMapping(value = {"/edit/{id}"})
	public String editCourse(Model model,@PathVariable("id") int id) {
		Course course = courseService.findById(id);
		model.addAttribute("title", "Edit");
		model.addAttribute("submitForm", course);
		model.addAttribute("viewOnly", false);
		return "course-action";
	}
	@PostMapping(value = {"/save"})
	public String saveCourse(Model model,@ModelAttribute("submitForm") @Validated Course course
			, BindingResult result, HttpSession session ) {
		if(result.hasErrors()) {
			if(course.getId() != 0) {
				model.addAttribute("title", "Edit");
			}else {
				model.addAttribute("title", "Add");
			}
			model.addAttribute("submitForm", course);
			return "course-action";
		}
		if(course.getId() != 0) {
			try {
				courseService.update(course);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				courseService.add(course);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		return "redirect:/course/list/1";
	}
	@GetMapping(value = {"/delete/{id}"})
	public String deleteCourse(Model model,@PathVariable("id")int id,HttpSession session) {
		Course course = courseService.findById(id);
		try {
			courseService.delete(course);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}

		return "redirect:/course/list/1";
	}
}
