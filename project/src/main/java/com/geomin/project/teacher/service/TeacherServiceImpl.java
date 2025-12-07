package com.geomin.project.teacher.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomin.project.command.GameContentVO;
import com.geomin.project.command.HomeWorkVO;
import com.geomin.project.command.HomeworkHistoryVO;
import com.geomin.project.command.learnGroupVO;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	TeacherMapper teacherMapper;

	@Override
	public int RegistGroup(learnGroupVO vo) {
		// 학습 그룹 등록
		return teacherMapper.RegistGroup(vo);
	}

	@Override
	public ArrayList<learnGroupVO> learnGroupLook() {
		// 학습 그룹 조회
		return teacherMapper.learnGroupLook();
	}

	// 내 그룹 조회
	@Override
	public ArrayList<learnGroupVO> myGroupList(int user_no) {
		
		return teacherMapper.myGroupList(user_no);
	}

	@Override
	public int RegistHomework(HomeWorkVO vo) {
		
		return teacherMapper.RegistHomework(vo);
	}

	@Override
	public learnGroupVO groupDetail(int sg_no) {
		
		return teacherMapper.groupDetail(sg_no);
	}

	@Override
	public ArrayList<HomeWorkVO> getHomework() {
		
		return teacherMapper.getHomework();
	}

	@Override
	public ArrayList<learnGroupVO> groupDetail2(int sg_no) {
		
		return teacherMapper.groupDetail2(sg_no);
	}

	@Override
	public int approve(int sg_no, int user_no) {
		
		return teacherMapper.approve(user_no, sg_no);
	}

	@Override
	public int capacity(int sg_no) {
		
		return teacherMapper.capacity(sg_no);
	}

	@Override
	public int reject(int sg_no, int user_no) {
		
		return teacherMapper.reject(user_no, sg_no);
	}

	@Override
	public ArrayList<HomeWorkVO> getMyHomework(String user_no) {
		
		return teacherMapper.getMyHomework(user_no);
	}

	@Override
	public ArrayList<learnGroupVO> mygroupguys(int sg_no) {
		
		return teacherMapper.mygroupguys(sg_no);
	}

	@Override
	public int homeworkSend(Map<String, Object> homework) {
		
		return teacherMapper.homeworkSend(homework);
	}

	@Override
	public ArrayList<HomeworkHistoryVO> homeworkReceive(int homework_no) {
		
		return teacherMapper.homeworkReceive(homework_no);
	}

	@Override
	public int homeworkGrade(Map<String, Object> homework) {
		
		return teacherMapper.homeworkGrade(homework);
	}

//	@Override
//	public int capaMinus(int sg_no) {
//		
//		return teacherMapper.capaMinus(sg_no);
//	}

	
	

}
