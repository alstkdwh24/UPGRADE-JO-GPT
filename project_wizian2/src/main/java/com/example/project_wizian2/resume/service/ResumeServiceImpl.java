package com.example.project_wizian2.resume.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project_wizian2.command.ResumeVO;

import java.util.List;

@Service("resume")
public class ResumeServiceImpl implements ResumeService{
	
	@Autowired
	ResumeMapper resumeMapper;

	@Override
	public int registResume(ResumeVO vo) {
		System.out.println("엄");
		
		return resumeMapper.registResume(vo);
	}

}
