package com.example.project_wizian2.resume.service;

import org.apache.ibatis.annotations.Mapper;

import com.example.project_wizian2.command.ResumeVO;

import java.util.List;

@Mapper
public interface ResumeMapper {
	
	public int registResume(ResumeVO vo);
	

}
