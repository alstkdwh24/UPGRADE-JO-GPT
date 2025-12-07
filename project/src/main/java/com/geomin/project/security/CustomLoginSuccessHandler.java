package com.geomin.project.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private String redirectURL;
	private final Integer Session_Timeout = 120 * 300;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		
		
		System.out.println("로그인 성공");
		//String path = request.getContextPath();
		//System.out.println(path);
	
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		String userRole = userDetails.getAuthorities().iterator().next().getAuthority();
		
		if(userRole.equals("ROLE_GEN")) {
			CustomLoginSuccessHandler custom = new CustomLoginSuccessHandler();
			//custom.setRedirectURL("http://localhost:9494/frame__user");
			request.getSession().setMaxInactiveInterval(Session_Timeout);
			response.sendRedirect(redirectURL + "/user/main");
		
		}else if (userRole.equals("ROLE_STU")) {
			CustomLoginSuccessHandler custom = new CustomLoginSuccessHandler();
			//custom.setRedirectURL("http://localhost:9494/frame__student");
			request.getSession().setMaxInactiveInterval(Session_Timeout);
			response.sendRedirect(redirectURL + "/student/main");
			
		}else if (userRole.equals("ROLE_TEA")) {
			CustomLoginSuccessHandler custom = new CustomLoginSuccessHandler();
			//custom.setRedirectURL("http://localhost:9494/frame__student");
			request.getSession().setMaxInactiveInterval(Session_Timeout);
			response.sendRedirect(redirectURL + "/teacher/main");
		
		}else if(userRole.equals("ROLE_ADMIN")) {
			CustomLoginSuccessHandler custom = new CustomLoginSuccessHandler();
			//custom.setRedirectURL("http://localhost:9494/frame__student");
			request.getSession().setMaxInactiveInterval(Session_Timeout);
			response.sendRedirect(redirectURL + "/admin/main");
		}
		
	}

	public String getRedirectURL() {
		return redirectURL;
	}
	
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
	

}
