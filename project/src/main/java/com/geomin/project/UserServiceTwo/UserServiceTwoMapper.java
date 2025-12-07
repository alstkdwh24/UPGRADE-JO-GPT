package com.geomin.project.UserServiceTwo;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.geomin.project.command.GameContentVO;
import com.geomin.project.util.JTwoCriteria;

@Mapper
public interface UserServiceTwoMapper {
	ArrayList<GameContentVO> MyList(JTwoCriteria JTwo);
	int JTwoTotal(JTwoCriteria JTwo);
}
