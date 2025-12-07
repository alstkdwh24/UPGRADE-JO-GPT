package com.geomin.project.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.geomin.project.command.UserVO;


public class MyUserDetails implements UserDetails{

	private final UserVO userVO;
	

	public MyUserDetails(UserVO vo) {
		this.userVO = vo;
	}
	
	public String getUser_role() {
		
		return userVO.getUser_role();
	}
	
	public UserVO getUserVO() {
		System.out.println("uservo인데, 이거 받아옴?");
		return userVO;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new GrantedAuthority() {
	
			@Override
			public String getAuthority() {
				return userVO.getUser_role();
			}
		});
		return list;
		
	}

	@Override
	public String getPassword() {
		return userVO.getUser_pw();
	}

	@Override
	public String getUsername() {
		return userVO.getUser_id();
	}

//	public Integer getUser_no(){
//		return userVO.getUser_no();
//	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public Integer getUser_no() {
		return userVO.user_no;
	}
}
