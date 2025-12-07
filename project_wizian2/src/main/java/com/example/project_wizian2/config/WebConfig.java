package com.example.project_wizian2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.project_wizian2.interceptor.ComAuthHandler;
import com.example.project_wizian2.interceptor.ManAuthHandler;
import com.example.project_wizian2.interceptor.StuAuthHandler;
import com.example.project_wizian2.interceptor.UserAuthHandler;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private UserAuthHandler userAuthHandler;
	@Autowired
	private StuAuthHandler stuAuthHandler;
	@Autowired
	private ComAuthHandler comAuthHandler;
	@Autowired
	private ManAuthHandler manAuthHandler;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
			registry.addInterceptor(userAuthHandler)
					.excludePathPatterns("/user/**")
					.excludePathPatterns("/")
					.addPathPatterns("/user_stu/**")
					.addPathPatterns("/user_mn/**")
					.addPathPatterns("/user_co/**");
			
			
					
					
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
		
	
}
