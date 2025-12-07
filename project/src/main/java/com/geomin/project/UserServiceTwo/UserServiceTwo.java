package com.geomin.project.UserServiceTwo;

import java.util.ArrayList;

import com.geomin.project.command.GameContentVO;
import com.geomin.project.util.JTwoCriteria;

public interface UserServiceTwo {
	ArrayList<GameContentVO> MyList(JTwoCriteria JTwo);
	int JTwoTotal(JTwoCriteria JTwo);
}
