package com.geomin.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.geomin.project.user.service.UserService;

@RestController
public class CheckIdController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/data/{user_id}")
	// 배포 시, CrossOrigin 설정 다시 해야함
	public int idCheck(@PathVariable("user_id") String id) {
		
		return userService.buttonIdCheck(id);
	}

}
