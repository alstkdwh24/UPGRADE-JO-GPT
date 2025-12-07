package com.example.project_wizian2.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project_wizian2.command.JobVO;
import com.example.project_wizian2.command.ManagerResumeListVO;
import com.example.project_wizian2.command.ResumeVO;
import com.example.project_wizian2.job.service.JobMapper;

@Service("manager")
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	private ManagerMapper managerMapper;
	

	@Override
	public ArrayList<ManagerResumeListVO> getList() {
		
		return managerMapper.getList();
	}

	@Override
	public ArrayList<ResumeVO> umList() {
		
		return managerMapper.umList();
	}


	@Override
	public ArrayList<ResumeVO> resumeDetail(int resume_num) {
		
		return managerMapper.resumeDetail(resume_num);
	}
	
	// 채용공고 승인 여부
	@Override
	public void updatePostStatus(String title, String yn) {
		
		managerMapper.updatePostStatus(title, yn);
	}
	
	// 2. 승인된 공고만 화면 표출
	@Override
	public ArrayList<ManagerResumeListVO> updatePost() {
		
		return managerMapper.updatePost();
	}

	// 3. 거절 시, 공고 삭제
	@Override
	public void deletePost(String title) {
		
		managerMapper.deletePost(title);
	}

	// 회사 메인 페이지와 공고등록 현황페이지에서 현재 등록 현황 확인
	@Override
	public ArrayList<ManagerResumeListVO> uploadPost() {
		
		return managerMapper.uploadPost();
	}

	// 학생 이력서 업데이트
	@Override
	public void updateResume(String stu_name, String stu_yn) {

		managerMapper.updateResume(stu_name, stu_yn);
		
	}

	@Override
	public ArrayList<ResumeVO> umList2() {
		
		return managerMapper.umList2();
	}

	// 서류 합격자 명단 ( y )
	@Override
	public ArrayList<ResumeVO> umList3() {
		
		return managerMapper.umList3();
	}

	// 1차 면접 합격 및 최종 면접 진출자 명단
	@Override
	public ArrayList<ResumeVO> umList4() {
		
		return managerMapper.umList4();
	}
	
	// 최종 합격자 명단
	@Override
	public ArrayList<ResumeVO> umList5() {
		
		return managerMapper.umList5();
	}

	// 입사 지원 확인
	@Override
	public ArrayList<JobVO> applyList(String stu_id) {
		System.out.println(stu_id);
		return managerMapper.applyList(stu_id);
	}

	@Override
	public List<String> searchId(String searchId) {
		return managerMapper.searchIdList(searchId);
	}
	
	// 기업 -> 지원자 리스트 확인
	@Override
	public ArrayList<ResumeVO> applyStu(String search_id) {
		
		return managerMapper.applyStu(search_id);
	}
	
	// 기업 -> 지원자 리스트 확인 ( 서류 합격 )
	@Override
	public ArrayList<ResumeVO> applyStu2(String search_id) {
		
		return managerMapper.applyStu2(search_id);
	}
	
	// 기업 -> 지원자 리스트 확인 ( 면접 전행 )
	@Override
	public ArrayList<ResumeVO> applyStu3(String search_id) {
		
		return managerMapper.applyStu3(search_id);
	}
	
	// 기업 -> 지원자 리스트 확인 ( 면접 전행 )
	@Override
	public ArrayList<ResumeVO> applyStu4(String search_id) {
		
		return managerMapper.applyStu4(search_id);
	}


	
	
	
	

}
