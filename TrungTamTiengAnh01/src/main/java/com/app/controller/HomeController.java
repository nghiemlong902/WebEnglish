package com.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.model.Course;
import com.app.model.Users;
import com.app.service.RoomUserService;
import com.app.service.UserService;
import com.app.utils.Constant;
import com.app.validator.LoginValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class HomeController {
	@Autowired
	UserService userService;
	@Autowired
	LoginValidator loginValidator;
	@Autowired
	RoomUserService roomUserService;


	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		if(dataBinder.getTarget().getClass() == Users.class) {
			dataBinder.setValidator(loginValidator);
		}
	}

	@GetMapping(value = {"/","/index","/home"})
	public String index(ModelMap map) {

		return "index";
	}
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new Users());
		model.addAttribute("searchForm", new Course());
		return "login";
	}



	@GetMapping("/access-denied")
	public String accessDenied(Model model) {
		return "access-denied";
	}
	@PostMapping("/processLogin")
	public String processLogin(Model model, @ModelAttribute("loginForm") @Validated Users userDTO, BindingResult result,HttpSession
			session) {
		if(result.hasErrors()) {
			model.addAttribute("searchForm", new Course());
			return "login";
		}
		Users userInfo = userService.getAllByProperty("username", userDTO.getUsername()).get(0);
		//Users userInfo = userService.findByUsername(userDTO.getUsername()) ;
		session.setAttribute(Constant.USER_INFO,userInfo);
		return "redirect:/index";
	}
	@GetMapping("/logout")
	public String login(HttpSession session) {
		session.removeAttribute(Constant.USER_INFO);
		return "redirect:/index";
	}


	@GetMapping("/calendar")
	public String calendar(HttpSession session, Model model) {
		ObjectMapper mapper = new ObjectMapper();
		Users users = (Users) session.getAttribute(Constant.USER_INFO);
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(new Date());

		List<Map<String, Object>> calendars = roomUserService.getCalendar(users.getRoleId(), users.getId(), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
		for(Map<String, Object> item: calendars) {
			item.put("start", dateTimeFormat.format(item.get("start")));
			item.put("end", dateTimeFormat.format(item.get("end")));
			//dateTimeFormat
		}
		try {
			String jsonCalendar = mapper.writeValueAsString(calendars);
			model.addAttribute("jsonCalendar", jsonCalendar);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "calendar";
	}

	@GetMapping("/statistics/chart")
	public String statistics(HttpSession session, Model model) {
		initBarChart(model);
		return "chart";
	}

	private void initBarChart(Model model) {
		 ObjectMapper mapper = new ObjectMapper();
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(new Date());

		 Map<Integer,Object> mapReceipt = new  HashMap();

		 for(Integer i = 1 ; i <= 12 ;i++) {
			 mapReceipt.put(i, 0);
		 }
		 //theo thang
		 List<Map<String, Object>> maps = roomUserService.getBarChartByMonth(calendar.get(Calendar.YEAR));
		 for(Map<String,Object> item :	maps) {
			 mapReceipt.put(Integer.parseInt(item.get("month").toString()), item.get("totalPrice"));
		 }
		 List<Map<String,Object>> lstObjectPrice = new ArrayList<>();
		 int total = 0;
		 for(Integer key : mapReceipt.keySet()) {
			 Map<String,Object> receipt = new  HashMap();
			 receipt.put("label", key);
			 receipt.put("value", mapReceipt.get(key));
			 lstObjectPrice.add(receipt);
		 }

		try {
			String jsonPriceMonth = mapper.writeValueAsString(lstObjectPrice);
			System.out.println(jsonPriceMonth);
			model.addAttribute("barcharJsonPriceMonth", jsonPriceMonth);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
