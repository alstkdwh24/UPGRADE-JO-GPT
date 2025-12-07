package com.geomin.project.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.geomin.project.command.UserVO;
import com.geomin.project.user.service.UserMapper;
import com.geomin.project.security.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserMapper userMapper;
	
	// 세션 주입하기 위한 HttpServletRequest(from 지원)
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		
		
		System.out.println("사용자가 로그인을 시도함");
		System.out.println("사용자가 입력한 값:" + user_id);
		
		UserVO vo = userMapper.login(user_id);
		
		
		
		if(vo != null) {
			System.out.println(  new MyUserDetails(vo) + " ++  " + vo.getUser_id() );
			MyUserDetails details = new MyUserDetails(vo);
			
			// 세션 저장(from 지원)
			HttpSession session = request.getSession();
			session.setAttribute("vo", vo);
			
			return new MyUserDetails(vo); 
		}
		
		return null;
	}

}
