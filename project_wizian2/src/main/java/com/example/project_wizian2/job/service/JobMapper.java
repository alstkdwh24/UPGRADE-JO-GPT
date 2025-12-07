package com.example.project_wizian2.job.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.project_wizian2.command.JobVO;
import com.example.project_wizian2.util.Criteria;



@Mapper
public interface JobMapper {
	
	

	public int regist(JobVO vo);

	public JobVO select(int prodd_id);
	public ArrayList<JobVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	public void delete(int prodd_id);
	public int update(JobVO vo);
	
	public void applyCompany(@Param("stu_id") String stu_id, @Param("prodd_id") int prodd_id);
	
}
