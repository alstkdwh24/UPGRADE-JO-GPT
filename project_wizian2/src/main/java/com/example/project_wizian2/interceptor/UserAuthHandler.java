package com.example.project_wizian2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.project_wizian2.command.ManagerVO;
import com.example.project_wizian2.command.StudentVO;

@Component
public class UserAuthHandler implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		HttpSession session = request.getSession();
		String stu_id = (String) session.getAttribute("user_id");
		StudentVO stu_vo = (StudentVO) session.getAttribute("stu_vo");
		//System.out.println(stu_vo.getStu_id() + "11111");
		//System.out.println(vo.getStu_id().equals(user_id));
		//System.out.println(stu_id);
		

		if(stu_id != null) {
			
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
