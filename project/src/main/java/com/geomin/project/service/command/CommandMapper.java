package com.geomin.project.service.command;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.geomin.project.command.GameContentVO;
import com.geomin.project.util.JCriteria;

@Mapper
public interface CommandMapper {
	ArrayList<GameContentVO> getList(JCriteria JCri);
	int getTotal(JCriteria JCri);

	//민상이 한거
}
