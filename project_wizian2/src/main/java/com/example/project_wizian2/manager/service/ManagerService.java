package com.example.project_wizian2.manager.service;

import java.util.ArrayList;
import java.util.List;

import com.example.project_wizian2.command.JobVO;
import com.example.project_wizian2.command.ManagerResumeListVO;
import com.example.project_wizian2.command.ResumeVO;

public interface ManagerService {

	public ArrayList<ManagerResumeListVO> getList();

	public ArrayList<ResumeVO> umList();
	public ArrayList<ResumeVO> resumeDetail(int resume_num);


	// 1. 채용 공고 등록 승인 여부
	public void updatePostStatus(String title, String yn);
	// 2. 승인된 공고만 화면 표출
	public ArrayList<ManagerResumeListVO> updatePost();
	// 3. 거절 시 삭제
	public void deletePost(String title);


	// 회사 메인 페이지와 공고등록 현황페이지에서 현재 등록 현황 확인
	public ArrayList<ManagerResumeListVO> uploadPost();

	// 학생 이력서 업데이트
	public void updateResume(String stu_name, String stu_yn);

	public ArrayList<ResumeVO> umList2();

	// 서류합격자 명단
	public ArrayList<ResumeVO> umList3();
	// 1차 면접 합격 및 최종 면접 진출자 명단
	public ArrayList<ResumeVO> umList4();
	// 최종 합격자 명단
	public ArrayList<ResumeVO> umList5();


	// 입사 지원자 확인
	public ArrayList<JobVO> applyList(String stu_id);

	// 기업 -> 지원자 리시트 확인
	public ArrayList<ResumeVO> applyStu(String search_id);

	// 기업 -> 지원자 리시트 확인 ( 서류 합격 )
	public ArrayList<ResumeVO> applyStu2(String search_id);

	// 기업 -> 지원자 리시트 확인 ( 면접 전형 진출자 )
	public ArrayList<ResumeVO> applyStu3(String search_id);

	// 기업 -> 지원자 리시트 확인 ( 최종합격자 )
	public ArrayList<ResumeVO> applyStu4(String search_id);

	// 지원자 아이디 검색
	List<String> searchId(String searchId);

}