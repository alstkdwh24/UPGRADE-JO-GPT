package com.geomin.project.controller;

import java.util.ArrayList;

import com.geomin.project.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geomin.project.board.service.BoardRepository;
import com.geomin.project.document.service.DocumentService;
import com.geomin.project.gameContentService.GameContentService;
import com.geomin.project.util.Criteria;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {


	@Autowired
	@Qualifier("BoardService")
	private BoardRepository boardService;
	
	@Autowired
	@Qualifier("GameContentService")
	private GameContentService gameContentService;
	
	@Autowired
	@Qualifier("DocumentService")
	private DocumentService documentService;
	
	@GetMapping("/main")
	public String main(Model model, Criteria ciriteria, HttpSession session) {
		
		ArrayList<NoticeVO> noticeVO = boardService.getNotice();
		ArrayList<FaqVO> faqVO = boardService.getFaq();
		ArrayList<QnaVO> qnaVO = boardService.getQna();
		
		ArrayList<GameContentVO> list = gameContentService.getList(ciriteria);
		model.addAttribute("gameContent", list);
		
		ArrayList<DocumentVO> list2 = documentService.getList(ciriteria);
		model.addAttribute("Document", list2);
		
		model.addAttribute("noticeVO", noticeVO);
		model.addAttribute("faqVO", faqVO);
		model.addAttribute("qnaVO", qnaVO);

//		model.addAttribute("user_no",vo);
		return "main";
	}
	
}
