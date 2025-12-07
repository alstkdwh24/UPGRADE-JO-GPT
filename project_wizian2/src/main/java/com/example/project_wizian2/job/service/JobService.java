package com.example.project_wizian2.job.service;

import java.util.ArrayList;

import com.example.project_wizian2.command.JobVO;
import com.example.project_wizian2.util.Criteria;

public interface JobService {
	
	public int regist(JobVO vo);
	
	public JobVO select(int prodd_id);
	public ArrayList<JobVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	public int update(JobVO vo);
	public void delete(int prodd_id);

	// 입사지원
	public void applyCompany(String stu_id, int prodd_id);
	
}