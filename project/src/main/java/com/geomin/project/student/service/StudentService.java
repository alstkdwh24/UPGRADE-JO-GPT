package com.geomin.project.student.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.geomin.project.command.HomeWorkVO;
import com.geomin.project.command.ProgressVO;
import com.geomin.project.command.StudyGroupVO;
import com.geomin.project.command.learnGroupVO;
import com.geomin.project.util.StudyGroupCriteria;

public interface StudentService {
	
	//스터디 그룹 신청
    int groupApply(int user_no, int sg_no, int sg_level);
	
	//그룹 신청 리스트
    ArrayList<StudyGroupVO> groupList(int user_no, int sg_no);
	
	//스터디 그룹 신청 중복 조회
    int groupCheck(int user_no, int sg_no);
	
	//스터디 그룹 거절 조회
    ArrayList<StudyGroupVO> rejectCheck(int user_no, int sg_no);
	
	//그룹 재가입 신청
    int reapplyGroup(int user_no, int sg_no);
	
	//그룹 신청 리스트
    ArrayList<StudyGroupVO> groupApplyList(int user_no);
	
	//그룹 승인 완료시 조회 안하기
    int groupCheckingList(int user_no);

	//숙제 조회
    ArrayList<HomeWorkVO> getHomeworkList(int user_no);
	
	//숙제 디테일 조회
    ArrayList<HomeWorkVO> getHomeworkDetail(int user_no, int homework_no);

	//숙제 제출
    int homeworkSubmission(HomeWorkVO hwVO);
	
	//그룹 스터디 조회
    ArrayList<StudyGroupVO> getList(StudyGroupCriteria cri);
	int getTotal();
	
	//ai 체크
    ArrayList<StudyGroupVO> aiList(int user_no, int user_level);
	
    //숙제 포인트 +1
    int addPoint(int user_no, int homework_no);
    
    //숙제 포인트 총 합
    int sumPoint(int user_no, int sg_no);
    
    //sg_class 값 불러오기
    int getClassProgress(int sg_no);
    
    //homework_leftdate 계산
    void leftDate(long homework_leftdate, int user_no, int homework_no);
    
    //진도율 넣기
    void insertClassProgress(int user_no, int sg_no, int homework_point);
	
    //진도율 불러오기
    int totalHomeworkPoint(int user_no, int sg_no);
    
    //전체 학생 진도율 불러오기
    ArrayList<ProgressVO> allStudentPointList(int sg_no);
   
}
