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
import com.app.model.OrderCourse;
import com.app.model.request.OrderSearchRequest;
import com.app.service.CourseService;
import com.app.service.OrderService;
import com.app.utils.Constant;
import com.app.utils.Paging;

@Controller
public class OrderController {
	@Autowired
	CourseService courseService;
	@Autowired
	private OrderService orderService;


	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));

	}

	@PostMapping("/order-course")
	public String orderCourse(Model model, @ModelAttribute("submitForm") @Validated OrderCourse orderCourse, BindingResult result,HttpSession
			session) {
		if(result.hasErrors()) {
			model.addAttribute("submitForm", orderCourse);
			return "dat-hang";
		}
		Course course =  courseService.findById(orderCourse.getCourseId());
		orderCourse.setCourse(course);
		try {
			orderService.add(orderCourse);
			session.setAttribute(Constant.MSG_SUCCESS, " Đặt thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			session.setAttribute(Constant.MSG_SUCCESS, "Đặt thất bại ");
			e.printStackTrace();
		}
		return "redirect:/trang-chu";
	}

	@GetMapping(value = {"/list/","/list"})
	public String redirect() {
		return "redirect:/course/list/1";
	}
	@RequestMapping("/order-course/list/{page}")
	public String listCourse(Model model,@ModelAttribute("searchForm") OrderSearchRequest orderSearchRequest,@PathVariable("page") int page
			,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		List<OrderCourse> list = orderService.getAll(orderSearchRequest, paging);
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
		return "order-list";
	}
}
