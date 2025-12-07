package com.example.project_wizian2.job.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project_wizian2.command.JobVO;
import com.example.project_wizian2.util.Criteria;


@Service("jobService")
public class JobServiceImpl implements JobService{
	@Autowired
	
	private JobMapper jobMapper;
	@Override
	public int regist(JobVO vo) {
		return jobMapper.regist(vo);
	}
	@Override
	public JobVO select(int prodd_id) {
		return jobMapper.select(prodd_id);
	}

	@Override
	public ArrayList<JobVO> getList(Criteria cri) {
		return jobMapper.getList(cri);
	}
	@Override
	public int getTotal(Criteria cri) {
		return jobMapper.getTotal(cri);
	}
	@Override
	public int update(JobVO vo) {
		return jobMapper.update(vo);
	}
	@Override
	public void delete(int prodd_id) {
		jobMapper.delete(prodd_id);
	}
	
	
	// 입사지원
	@Override
	public void applyCompany(String stu_id, int prodd_id) {
		jobMapper.applyCompany(stu_id, prodd_id);
	}


	
	
}
	


