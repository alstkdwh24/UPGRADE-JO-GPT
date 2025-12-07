package com.geomin.project.board.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.geomin.project.command.FaqVO;
import com.geomin.project.command.NoticeVO;
import com.geomin.project.command.QnaVO;
import com.geomin.project.util.CriteriaInqury;

public interface BoardService {

	// 공지사항 등록
    int regist(NoticeVO vo);
	// faq 등록
    int faqRegist(FaqVO vo);
	// qna 등록
    int qnaRegist(QnaVO vo);
	
	// 공지사항 조회
    ArrayList<NoticeVO> getNotice();
	// faq 조회
    ArrayList<FaqVO> getFaq();
	// qna 조회
    ArrayList<QnaVO> getQna();
	
	//공지사항 삭제
    int NoticeDelete(int notice_no);
	//faq 삭제
    int FaqDelete(int faq_no);
	//qna삭제
    int qnaDelete(int qna_no);
	
	// 공지사항 수정
    NoticeVO noticeModify(int notice_no);
	int noticeModifyUpdate(NoticeVO vo);
	
	//faq 수정
    FaqVO faqModify(int faq_no);
	int faqModifyUpdate(FaqVO vo);
	
	// qna 수정
    QnaVO qnaModify(int qna_no);
	int qnaModifyUpdate(QnaVO vo);
	
	// qna 조회
    ArrayList<QnaVO> getQna2(@Param("criteria") CriteriaInqury criteria);
	int getTotal();

	ArrayList<QnaVO> getQna_two();
}
