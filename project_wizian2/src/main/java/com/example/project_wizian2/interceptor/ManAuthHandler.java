package com.example.project_wizian2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ManAuthHandler implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String auth = (String) session.getAttribute("auth");
		System.out.println(auth);
	
		if(auth.equals("man")) {
			return true;
		}else {
			response.sendRedirect(request.getContextPath()+ "/user/login");
			return false;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
	
	
}
