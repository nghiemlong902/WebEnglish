package com.app.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.Users;
import com.app.service.UserService;
import com.app.utils.Constant;
import com.app.validator.UserValidator;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	UserService userService;

	@Autowired
	UserValidator userValidator;



	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		if(dataBinder.getTarget().getClass() == Users.class) {
			dataBinder.setValidator(userValidator);
		}
	}

	@GetMapping
	public String profile(Model model, HttpSession session) {
		Users users = (Users) session.getAttribute(Constant.USER_INFO);
		users = userService.findById(users.getId());
		model.addAttribute("submitForm", users);
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		return "profile";
	}

	@PostMapping("/save")
	public String saveProfile(Model model,@ModelAttribute("submitForm") @Validated Users users, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Profile");
			model.addAttribute("submitForm", users);
			return "profile";
		}
		try {
			userService.update(users);
			session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
		}

		return "redirect:/profile";
	}
}
