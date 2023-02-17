package com.app.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Users;
import com.app.service.UserService;
import com.app.utils.Constant;

public class FilterAdmin  implements HandlerInterceptor{

	@Autowired
	UserService userService;


	public boolean setUp(String servletPath , String admin) {
		return true;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session  =request.getSession();
		Users user = (Users) session.getAttribute(Constant.USER_INFO);

		if(user != null) {
			if(user.getRoleId() == Constant.ROLE_ADMIN) {
				return true ;
			}
			response.sendRedirect(request.getContextPath() + "/access-denied");
			return false;
		}else {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}



	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
