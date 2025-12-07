package com.geomin.project.service.command;

import java.util.ArrayList;

import com.geomin.project.command.GameContentVO;
import com.geomin.project.util.JCriteria;

public interface CommandService {
	ArrayList<GameContentVO> getList(JCriteria JCri);
	int getTotal(JCriteria JCri);

	//민상이 한거


}
