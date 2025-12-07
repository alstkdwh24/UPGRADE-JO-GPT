package com.geomin.project.teacher.service;

import java.util.ArrayList;
import java.util.Map;

import com.geomin.project.command.GameContentVO;
import com.geomin.project.command.HomeWorkVO;
import com.geomin.project.command.HomeworkHistoryVO;
import com.geomin.project.command.learnGroupVO;

public interface TeacherService {
	
	// 학습 그룹 등록
    int RegistGroup(learnGroupVO vo);
	
	// 학습 그룹 조회
    ArrayList<learnGroupVO> learnGroupLook();
	
	// 내 학습그룹 조회(숙제 페이지에 내 학습그룹 불러오기 포함)
    ArrayList<learnGroupVO> myGroupList(int user_no);
	
	// 숙제 등록
    int RegistHomework(HomeWorkVO vo);
	
	// 숙제 조회
    ArrayList<HomeWorkVO> getHomework();
	
	// 그룹 상세조회(join 걸었음)
    learnGroupVO groupDetail(int sg_no);
	
	// 그룹 상세조회 2(그룹 신청 이력 있을 때)
    ArrayList<learnGroupVO> groupDetail2(int sg_no);
	
	// 그룹 가입 승인
    int approve(int sg_no, int user_no);
	
	// 그룹 가입 승인 시 capacity + 1
    int capacity(int sg_no);
	
	// 그룹 가입 거절
    int reject(int sg_no, int user_no);
	
	// 그룹 가입 거절 시 capacity - 1
//	public int capaMinus(int sg_no);
	
	// 내가 만든 숙제들 확인(숙제 전송 페이지)
    ArrayList<HomeWorkVO> getMyHomework(String user_no);
	
	// 그룹 가입된 사람들 가져오기
    ArrayList<learnGroupVO> mygroupguys(int sg_no);
	
	// 숙제 내주기
    int homeworkSend(Map<String, Object> homework);
	
	// 숙제 제출 내역
    ArrayList<HomeworkHistoryVO> homeworkReceive(int homework_no);
	
	// 숙제 평가하고 저장 눌렀을 때
    int homeworkGrade(Map<String, Object> homework);
	
	

}
