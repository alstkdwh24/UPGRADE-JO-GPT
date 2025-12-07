package com.geomin.project.document.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.geomin.project.command.DocumentVO;
import com.geomin.project.command.GameContentVO;
import com.geomin.project.util.Criteria;

public interface DocumentService {

	int regist(DocumentVO vo, List<MultipartFile> list);
	
	// 학습 - 학습 자료 조회 - 내용
    ArrayList<DocumentVO> getList(Criteria criteria);
	int getTotal();
	
	// 게임 컨텐츠 삭제 - del_check 값 N으로 변경
    int learnContentDelete(int docu_no);
	
	// 학습 - 학습 자료 조회 - 내용
    ArrayList<DocumentVO> delList(Criteria criteria);
	int getNoTotal();
	
	// 학습 수정
    DocumentVO docuList(int docu_no);
	int docuUpdate(DocumentVO vo, List<MultipartFile> list);
}
