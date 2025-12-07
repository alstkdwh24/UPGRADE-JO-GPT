package com.geomin.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geomin.project.board.service.BoardRepository;
import com.geomin.project.command.FaqVO;
import com.geomin.project.command.NoticeVO;
import com.geomin.project.command.QnaVO;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	@Qualifier("BoardService")
	private BoardRepository boardService;
	
	// 메뉴 - 서비스
	@GetMapping("/service")
	public String service() {
		return "menu/service";
	}
	
	// 메뉴 - 게임
	@GetMapping("/game")
	public String game() {
		return "menu/game";
	}
	
	// 메뉴 - 교육영상
	@GetMapping("/eduVideo")
	public String eduVideo() {
		return "menu/eduVideo";
	}
	
	// 메뉴 - 센터
	@GetMapping("/center")
	public String center( Model model) {
		
		ArrayList<NoticeVO> noticeVo = boardService.getNotice();
		ArrayList<FaqVO> faqVo = boardService.getFaq();
		ArrayList<QnaVO> qnaVo = boardService.getQna();
		
		model.addAttribute("noticeVo" , noticeVo);
		model.addAttribute("faqVo" , faqVo);
		model.addAttribute("qnaVo" , qnaVo);
		
		return "menu/center";
	}
	
	// 메뉴 - 사이트맵
	@GetMapping("/sitemap")
	public String sitemap() {
		return "menu/sitemap";
	}
	
}
