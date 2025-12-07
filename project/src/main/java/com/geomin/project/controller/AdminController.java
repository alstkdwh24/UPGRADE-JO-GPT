package com.geomin.project.controller;




import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geomin.project.board.service.BoardRepository;
import com.geomin.project.board.service.BoardService;
import com.geomin.project.command.DocumentVO;
import com.geomin.project.command.FaqVO;
import com.geomin.project.command.GameContentVO;
import com.geomin.project.command.NoticeVO;
import com.geomin.project.command.PageVO;
import com.geomin.project.command.PageVOinqury;
import com.geomin.project.command.PageVOmember;
import com.geomin.project.command.PageVOprice;
import com.geomin.project.command.PageVOsleep;
import com.geomin.project.command.PurchaseVO;
import com.geomin.project.command.QnaVO;
import com.geomin.project.command.UserVO;
import com.geomin.project.document.service.DocumentService;
import com.geomin.project.gameContentService.GameContentService;
import com.geomin.project.user.service.UserService;
import com.geomin.project.util.Criteria;
import com.geomin.project.util.CriteriaInqury;
import com.geomin.project.util.CriteriaMember;
import com.geomin.project.util.CriteriaPrice;
import com.geomin.project.util.CriteriaSleep;



@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	@Qualifier("GameContentService")
	private GameContentService gameContentService;
	
	@Autowired
	@Qualifier("DocumentService")
	private DocumentService documentService;
	
	@Autowired
	@Qualifier("BoardService")
	private BoardRepository boardService;
	
	@Autowired
	private UserService userService;
	
	// 파일 업로드 경로
//	@Value("${project.upload.path}")
//	private String uploadPath;
	
	// 메인 화면 - 관리자 
	@GetMapping("/main")
	public String main(Model model, Criteria criteria) {
		
		//qna
		ArrayList<QnaVO> qnaList = boardService.getQna();
		model.addAttribute("qnaList", qnaList);
		
		//사진
		ArrayList<GameContentVO> list = gameContentService.getList(criteria);
		model.addAttribute("gameContent", list);
		
		return "admin/main";
	}
	
// 게임 컨텐츠 관련
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 게임 메뉴 - 컨텐츠 조회
	@GetMapping("/gameLook")
	public String gameLook(Model model, Criteria criteria) {
		
		ArrayList<GameContentVO> list = gameContentService.getList(criteria);
		int total = gameContentService.getTotal();
		PageVO vo = new PageVO(criteria, total);
		model.addAttribute("gameContent", list);
		model.addAttribute("pageVO", vo);
		return "admin/gameLook";
		
	}
	
	// 게임 메뉴 - 컨텐츠 등록
	@GetMapping("/gameRegist")
	public String gameRegist() {
		return "admin/gameRegist";
	}

	// 컨텐츠 등록 - 게임 컨텐츠 등록
	@PostMapping("/gameRegistForm")
	public String gameRegistForm(GameContentVO vo, RedirectAttributes ra, @RequestParam("inputFile") List<MultipartFile> list)  {
		
		// 사진 넘어올 때
		// 1. 공백 이미지 제거
		list = list.stream().filter(m -> !m.isEmpty()).collect(Collectors.toList());
		
		// 2. 이미지 타입인지 검사
		for(MultipartFile file : list) {
			
			if(!file.getContentType().contains("image")) {
				ra.addFlashAttribute("msg", "이미지는 필수로 선택합니다. png/jpg/jpeg/500x500만 가능합니다.");
				return "redirect:/admin/gameRegist";
			}
			
		}
		// 3.이미지를 올린 경우는 서비스로 위임
		int result = gameContentService.regist(vo, list);
		if(result == 1) { //성공
			ra.addFlashAttribute("msg", "정상적으로 처리되었습니다."); //리다이렉트시 1회성
		}else { //실패
			ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 8282-8282");
		}
		
		return "redirect:/admin/gameLook";
	}
	
	@GetMapping("/gameDeleteHistory")
	public String gameDeleteHistory(Model model, Criteria criteria) {
		
		ArrayList<GameContentVO> gameDeleteHistory = gameContentService.delHistory(criteria);
		int total = gameContentService.getNoTotal();
		PageVO vo = new PageVO(criteria, total);
		model.addAttribute("gameDeleteHistory", gameDeleteHistory);
		model.addAttribute("pageVO", vo);
		
		return "admin/gameDeleteHistory";
	}
	
	@GetMapping("/gameModify")
	public String gameContentModify(@RequestParam("game_no") int game_no, Model model) {
		GameContentVO vo = gameContentService.gameList(game_no);
		model.addAttribute("vo", vo);
		return "admin/gameModify";
	}
	
	@PostMapping("/gameModifyForm")
	public String gameModifyForm(GameContentVO vo, RedirectAttributes ra, @RequestParam("inputFile") List<MultipartFile> list) {
		
		list = list.stream().filter(m -> !m.isEmpty()).collect(Collectors.toList());
		// 2. 이미지 타입인지 검사
		for(MultipartFile file : list) {
			
			if(!file.getContentType().contains("image")) {
				ra.addFlashAttribute("msg", "이미지는 필수로 선택합니다. png/jpg/jpeg/500x500만 가능합니다.");
				return "redirect:/admin/gameModify";
			}
			
		}
		
		// 3.이미지를 올린 경우는 서비스로 위임
				int result = gameContentService.gameUpdate(vo, list);
				if(result == 1) { //성공
					ra.addFlashAttribute("msg", "정상적으로 처리되었습니다."); //리다이렉트시 1회성
				}else { //실패
					ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 8282-8282");
				}
		
		return "redirect:/admin/gameLook";
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 학습 메뉴 - 학습 자료 조회
	@GetMapping("/learnLook")
	public String learnLook(Model model, Criteria criteria) {
		
		ArrayList<DocumentVO> list = documentService.getList(criteria);
		int total = documentService.getTotal();
		PageVO vo = new PageVO(criteria, total);
		model.addAttribute("Document", list);
		model.addAttribute("pageVO", vo);
		
		return "admin/learnLook";
	}
	
	
	// 학습 메뉴 - 학습 자료 등록
	@GetMapping("/learnRegist")
	public String learnRegist(@RequestParam(value = "game_no", required = false) String game_no, Model model) {

		model.addAttribute("game_no",game_no);
		
		return "admin/learnRegist";
	}
	
	@PostMapping("/learnRegistForm")
	public String learnRegistForm(DocumentVO vo, RedirectAttributes ra, @RequestParam("inputFiles") List<MultipartFile> list) {
		
		
		// 사진 넘어올 때
		// 1. 공백 이미지 제거
		list = list.stream().filter(m -> !m.isEmpty()).collect(Collectors.toList());
		
		// 2. 이미지 타입인지 검사
		for(MultipartFile file : list) {
			
			if(!file.getContentType().contains("image")) {
				ra.addFlashAttribute("msg", "이미지는 필수로 선택합니다. png/jpg/jpeg/500x500만 가능합니다.");
				return "redirect:/admin/learnRegist";
			}
			
		}
		
		// 3.이미지를 올린 경우는 서비스로 위임
		int result = documentService.regist(vo, list);
		if(result == 1) { //성공
			ra.addFlashAttribute("msg", "정상적으로 처리되었습니다."); //리다이렉트시 1회성
		}else { //실패
			ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 8282-8282");
		}
		
		return "redirect:/admin/learnLook";
	}
	
	
	@GetMapping("/learnDeleteHistory")
	public String learnDeleteHistory(Model model, Criteria criteria) {
		
		ArrayList<DocumentVO> list =  documentService.delList(criteria);
		int total = documentService.getNoTotal();
		System.out.println(total + "total");
		PageVO vo = new PageVO(criteria, total);
		model.addAttribute("Document", list);
		model.addAttribute("pageVO", vo);
		
		return "admin/learnDeleteHistory";
	}
	
	
	// 학습 자료 수정
	
	@GetMapping("/learnModify")
	public String learnModify(@RequestParam("docu_no") int docu_no, Model model) {
		DocumentVO vo = documentService.docuList(docu_no);
		model.addAttribute("vo", vo);
		return "admin/learnModify";
	}
	
	@PostMapping("/learnModifyForm")
	public String learnModifyForm(DocumentVO vo, RedirectAttributes ra, @RequestParam("inputFiles") List<MultipartFile> list) {
		
		list = list.stream().filter(m -> !m.isEmpty()).collect(Collectors.toList());
		
		// 2. 이미지 타입인지 검사
		for(MultipartFile file : list) {
			
			if(!file.getContentType().contains("image")) {
				ra.addFlashAttribute("msg", "이미지는 필수로 선택합니다. png/jpg/jpeg/500x500만 가능합니다.");
				return "redirect:/admin/learnModify";
			}
			
		}
		// 3.이미지를 올린 경우는 서비스로 위임
		int result = documentService.docuUpdate(vo, list);
		if(result == 1) { //성공
			ra.addFlashAttribute("msg", "정상적으로 처리되었습니다."); //리다이렉트시 1회성
		}else { //실패
			ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 8282-8282");
		}
		return "redirect:/admin/learnLook";
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/boardLook")
	public String boardLook(Model model) {
		
		ArrayList<NoticeVO> noticeList = boardService.getNotice();
		ArrayList<FaqVO> faqList = boardService.getFaq();
		ArrayList<QnaVO> qnaList = boardService.getQna();
		model.addAttribute("noticeList" , noticeList);
		model.addAttribute("faqList", faqList);
		model.addAttribute("qnaList",qnaList);
		
		return "admin/boardLook";
	}
	
	
	// 게시판 - 공지사항 등록
	@GetMapping("/noticeRegist")
	public String noticeRegist() {
		return "admin/noticeRegist";
	}
	
	@PostMapping("/noticeRegistForm")
	public String noticeRegistForm(NoticeVO vo, RedirectAttributes ra) {
		int result = boardService.regist(vo);

		if(result == 1) {
			ra.addFlashAttribute("msg", "정상적으로 등록되었습니다.");
		}else {
			ra.addFlashAttribute("msg", "게시글 등록을 다시 확인요청드립니다.");
		}
		return "redirect:/admin/boardLook";
	}
	
	// 게시판 - 공지사항 삭제
	@GetMapping("/noticeDeleteForm")
	public String noticeDeleteForm(@RequestParam("notice_no") int notice_no) {
		
		boardService.NoticeDelete(notice_no);
		
		return "redirect:/admin/boardLook";
	}
	
	// 게시판 - 공지사항 수정
	@GetMapping("/noticeModify")
	public String noticeModify(@RequestParam("notice_no") int notice_no, Model model) {
		
		NoticeVO vo =  boardService.noticeModify(notice_no);
		model.addAttribute("vo", vo);
		return "admin/noticeModify";
	}
	
	@PostMapping("/noticeModifyForm")
	public String noticeModifyForm(NoticeVO vo, RedirectAttributes ra) {
		int result = boardService.noticeModifyUpdate(vo);
		
		if(result == 1) {
			ra.addFlashAttribute("msg", "정상적으로 등록되었습니다.");
			return "redirect:/admin/boardLook";
		}else {
			ra.addFlashAttribute("msg", "수정사항을 다시 확인요청드립니다.");
			return "admin/noticeModify";
		}
		
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// 게시판 - FAQ 등록
	@GetMapping("/faqRegist")
	public String faqRegist() {
		return "admin/faqRegist";
	}
	
	@PostMapping("/faqRegistForm")
	public String faqReistForm(FaqVO vo, RedirectAttributes ra) {
		
		int result = boardService.faqRegist(vo);
		if(result == 1) {
			ra.addFlashAttribute("msg", "정상적으로 등록되었습니다.");
		}else {
			ra.addFlashAttribute("msg", "게시글 등록을 다시 확인요청드립니다.");
		}
		
		return "redirect:/admin/boardLook";
	}
	
	// 게시판 - FAQ 삭제
	@GetMapping("/faqDeleteForm")
	public String faqDeleteForm(@RequestParam("faq_no") int faq_no) {

		boardService.FaqDelete(faq_no);

		return "redirect:/admin/boardLook";
	}
	
	@GetMapping("/faqModify")
	public String faqModify(@RequestParam("faq_no") int faq_no, Model model) {
		
		FaqVO vo = boardService.faqModify(faq_no);
		model.addAttribute("vo", vo);
		
		return "admin/faqModify";
	}
	
	@PostMapping("/faqModifyForm")
	public String faqModifyForm(FaqVO vo, RedirectAttributes ra) {
		int result = boardService.faqModifyUpdate(vo);
		if(result == 1) {
			ra.addFlashAttribute("msg", "정상적으로 등록되었습니다.");
			return "redirect:/admin/boardLook";
		}else {
			ra.addFlashAttribute("msg", "수정사항을 다시 확인요청드립니다.");
			return "admin/faqModify";
		}
		
		
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 게시판 - Q&A 등록
	@GetMapping("/qnaRegist")
	public String qnaRegist() {
		return "admin/qnaRegist";
	}
	
	@PostMapping("/qnaRegistForm")
	public String qnaRegistForm(QnaVO vo, RedirectAttributes ra) {
		int result = boardService.qnaRegist(vo);
		System.out.println("qna등록성공여부 : " + result);
		if(result == 1) {
			ra.addFlashAttribute("msg", "정상적으로 등록되었습니다.");
		}else {
			ra.addFlashAttribute("msg", "게시글 등록을 다시 확인요청드립니다.");
		}
		return "redirect:/admin/boardLook";
	}
	
	// 게시판 - qna 삭제
	@GetMapping("/qnaDeleteForm")
	public String qnaDeleteForm(@RequestParam("qna_no") int qna_no) {
		boardService.qnaDelete(qna_no);
		return "redirect:/admin/boardLook";
	}
	
	@GetMapping("/qnaModify")
	public String qnaModify(@RequestParam("qna_no") int qna_no, Model model) {
		QnaVO vo = boardService.qnaModify(qna_no);
		model.addAttribute("vo", vo);
		return "admin/qnaModify";
	}
	
	@PostMapping("/qnaModifyForm")
	public String qnaModifyForm(QnaVO vo, RedirectAttributes ra) {
		
		int result = boardService.qnaModifyUpdate(vo);
		if(result == 1) {
			ra.addFlashAttribute("msg", "정상적으로 등록되었습니다.");
		}else {
			ra.addFlashAttribute("msg", "Q&A 수정을 다시 확인요청드립니다.");
		}
		
		return "redirect:/admin/boardLook";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/member")
	public String member(Model model, CriteriaMember criteria) {
		ArrayList<UserVO> list = userService.getList(criteria);
		int total = userService.getTotal();
		PageVOmember vo = new PageVOmember(criteria, total);
		
		model.addAttribute("UserList", list);
		model.addAttribute("pageVO", vo);
		
		return "admin/member";
	}
	
	@GetMapping("/userSelect")
	@ResponseBody
	public UserVO userSelect(@RequestParam("user_no") int user_no, Model model) {
		System.out.println("가져온 번호 : " + user_no);
		UserVO vo = userService.findUser(user_no);
		
		return vo;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 문의사항 조회
	@GetMapping("/inqury")
	public String inqury(Model model, CriteriaInqury criteria) {

		ArrayList<QnaVO> qnaList = boardService.getQna2(criteria);
		int total = boardService.getTotal();
		PageVOinqury vo = new PageVOinqury(criteria, total);
		
		model.addAttribute("qnaList",qnaList);
		model.addAttribute("pageVO", vo);
		
		return "admin/inqury";
	}
	
	// 휴먼 계정 전환
	@GetMapping("/userSleep")
	public String userSleep(@RequestParam("userSleepNo") String userSleepNo) {
		
		int user_no = Integer.parseInt(userSleepNo);
		
		userService.userSleep(user_no);
		
		return "redirect:/admin/member";
	}
	
	// 휴먼 계정 조회
	@GetMapping("/memberSleep")
	public String memberSleep(Model model, CriteriaSleep criteria) {
		
		ArrayList<UserVO> memberSleepList = userService.getSleepList(criteria);
		int total = userService.getSleepTotal();
		PageVOsleep vo = new PageVOsleep(criteria, total);
		
		model.addAttribute("memberSleepList",memberSleepList);
		model.addAttribute("pageVO", vo);
		
		return "admin/memberSleep";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 매출조회
	@GetMapping("/priceLook")
	public String priceLook(Model model, CriteriaPrice criteria) {
		
		ArrayList<GameContentVO> list = userService.getPurchaseList(criteria);
		int total = userService.getPriceTotal();
		PageVOprice vo = new PageVOprice(criteria, total);
		
		model.addAttribute("list",list);
		model.addAttribute("pageVO", vo);
		
		return "admin/priceLook";
	}
	
	
	
	
}
