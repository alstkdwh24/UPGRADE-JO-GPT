package com.geomin.project.UserServiceTwo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.geomin.project.command.GameContentVO;
import com.geomin.project.util.JTwoCriteria;

@Service("userServiceTwo")
public class UserServiceTwoImpl implements UserServiceTwo {

	@Autowired
	@Qualifier("userServiceTwoMapper")
	private UserServiceTwoMapper userServiceTwoMapper;

	@Override
	public ArrayList<GameContentVO> MyList(JTwoCriteria JTwo) {
		// TODO Auto-generated method stub
		return userServiceTwoMapper.MyList(JTwo);
	}

	@Override
	public int JTwoTotal(JTwoCriteria JTwo) {
		// TODO Auto-generated method stub
		return userServiceTwoMapper.JTwoTotal(JTwo);
	}







}
