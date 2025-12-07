package com.geomin.project.user.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomin.project.command.GameContentVO;
import com.geomin.project.command.PurchaseVO;
import com.geomin.project.command.QnaVO;
import com.geomin.project.command.UserVO;
import com.geomin.project.util.Criteria;
import com.geomin.project.util.CriteriaMember;
import com.geomin.project.util.CriteriaPrice;
import com.geomin.project.util.CriteriaQuestion;
import com.geomin.project.util.CriteriaSleep;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	// 회원가입
	@Override
	public int regist(UserVO vo) {
		return userMapper.regist(vo);
	}

	// 회원가입 - 아이디 중복 조회
	@Override
	public int buttonIdCheck(String id) {

		return userMapper.buttonIdCheck(id);
	}

	 
	 // 회원 정보 수정
	  
	 @Override 
	 public int modify(UserVO vo) { 
		 System.out.println("임플먼트 vo 값 :" +vo.toString()); 
		 
		 return userMapper.modify(vo); 
		 
	 }

	// 유저 조회
	@Override
	public ArrayList<UserVO> getList(CriteriaMember criteria) {
		
		return userMapper.getList(criteria);
	}

	@Override
	public int getTotal() {
		return userMapper.getTotal();
	}

	// 회원 검색
	@Override
	public UserVO findUser(int user_no) {
		
		return userMapper.findUser(user_no);
	}

	// Q&A 조회
	@Override
	public ArrayList<QnaVO> getQnaList(int user_no,CriteriaQuestion criteria ) {
		
		return userMapper.getQnaList(user_no, criteria );
	}

	@Override
	public int getQnaTotal(int user_no) {
		
		return userMapper.getQnaTotal(user_no);
	}

	// Q&A 등록
	@Override
	public int qnaRegist(QnaVO vo) {
		
		return userMapper.qnaRegist(vo);
	}

	// Q&A 삭제
	@Override
	public int qnaDelete(int qna_no) {
		
		return userMapper.qnaDelete(qna_no);
	}

	// 휴먼 계정 전환
	@Override
	public int userSleep(int user_no) {
		
		return userMapper.userSleep(user_no);
	}

	// 휴먼 계정 조회
	@Override
	public ArrayList<UserVO> getSleepList(CriteriaSleep criteria) {
		
		return userMapper.getSleepList(criteria);
	}

	@Override
	public int getSleepTotal() {
		return userMapper.getSleepTotal();
	}

	// 매출 조회
	@Override
	public ArrayList<GameContentVO> getPurchaseList(CriteriaPrice criteria) {
		// TODO Auto-generated method stub
		return userMapper.getPurchaseList(criteria);
	}

	@Override
	public int getPriceTotal() {
		// TODO Auto-generated method stub
		return userMapper.getPriceTotal();
	}

	// 매출 조회
	@Override
	public ArrayList<PurchaseVO> getPurchaseDetail(String purchase_date) {

		return userMapper.getPurchaseDetail(purchase_date);
	}

}
