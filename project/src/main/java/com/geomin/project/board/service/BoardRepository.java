package com.geomin.project.board.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geomin.project.command.FaqVO;
import com.geomin.project.command.NoticeVO;
import com.geomin.project.command.QnaVO;
import com.geomin.project.util.CriteriaInqury;

@Service("BoardService")
public class BoardRepository implements BoardService{

	@Autowired
	private BoardMapper boardMapper;
	
	// 공지사항 등록
	@Override
	public int regist(NoticeVO vo) {
		return boardMapper.regist(vo);
	}

	// 공지사항 조회
	@Override
	public ArrayList<NoticeVO> getNotice() {
		return boardMapper.getNotice();
	}
	
	// 공지사항 삭제
	@Override
	public int NoticeDelete(int notice_no) {
		return boardMapper.NoticeDelete(notice_no);
	}
	
	// 공지사항 수정 조회
	@Override
	public NoticeVO noticeModify(int notice_no) {
		
		return boardMapper.noticeModify(notice_no);
	}
	
	@Override
	public int noticeModifyUpdate(NoticeVO vo) {
		
		return boardMapper.noticeModifyUpdate(vo);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// faq 등록
	@Override
	public int faqRegist(FaqVO vo) {
		
		return boardMapper.faqRegist(vo);
	}
	
	//faq 조회
	@Override
	public ArrayList<FaqVO> getFaq() {
		return boardMapper.getFaq();
	}
	
	// faq 삭제
	@Override
	public int FaqDelete(int faq_no) {
		return boardMapper.FaqDelete(faq_no);
	}
	
	// faq 수정 조회
	@Override
	public FaqVO faqModify(int faq_no) {
		return boardMapper.faqModify(faq_no);
	}
	
	@Override
	public int faqModifyUpdate(FaqVO vo) {
		return boardMapper.faqModifyUpdate(vo);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// qna 등록
	@Override
	public int qnaRegist(QnaVO vo) {
		
		return boardMapper.qnaRegist(vo);
	}
	//qna 조회
	@Override
	public ArrayList<QnaVO> getQna() {
		
		return boardMapper.getQna();
	}
	
	// qna 삭제
	@Override
	public int qnaDelete(int qna_no) {
		return boardMapper.qnaDelete(qna_no);
	}

	// qna 수정
	@Override
	public QnaVO qnaModify(int qna_no) {
		return boardMapper.qnaModify(qna_no);
	}

	@Override
	public int qnaModifyUpdate(QnaVO vo) {
		
		return boardMapper.qnaModifyUpdate(vo);
	}

	
	@Override
	public ArrayList<QnaVO> getQna2(CriteriaInqury criteria) {
		
		return boardMapper.getQna2(criteria);
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return boardMapper.getTotal();
	}

	@Override
	public ArrayList<QnaVO> getQna_two() {
		return boardMapper.getQna_two();
	}


}
