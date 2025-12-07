package com.geomin.project.service.command;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomin.project.command.GameContentVO;
import com.geomin.project.util.JCriteria;
@Service("commandService")
public class CommandServiceImpl implements CommandService {
	//민상이 한거

	@Autowired
	private CommandMapper commandMapper;
	@Override
	public ArrayList<GameContentVO> getList(JCriteria JCri) {
		// TODO Auto-generated method stub
		return commandMapper.getList(JCri);
	}
	@Override
	public int getTotal(JCriteria JCri) {
		// TODO Auto-generated method stub
		return commandMapper.getTotal(JCri);
	}



}
