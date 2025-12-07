package com.geomin.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geomin.project.board.service.BoardService;
import com.geomin.project.cart.service.CartService;
import com.geomin.project.command.GameContentVO;
import com.geomin.project.command.HomeWorkVO;
import com.geomin.project.command.PageVO;
import com.geomin.project.command.PurchaseVO;
import com.geomin.project.command.QnaVO;
import com.geomin.project.command.UserVO;
import com.geomin.project.command.learnGroupVO;
import com.geomin.project.gameContentService.GameContentService;
import com.geomin.project.teacher.service.TeacherService;


@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private GameContentService gameContentService;
	
	@Autowired
	BoardService boardService;

	// 메인 화면
	@GetMapping("/main")
	public String main(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		model.addAttribute("vo", vo);
		System.out.println(vo.toString());
		ArrayList<QnaVO> qnaList = boardService.getQna();
		model.addAttribute("qnaList", qnaList);
		
		return "teacher/main";
	}
	
	// 숙제 등록 페이지
	@GetMapping("/homeWorkRegist")
	public String homeWorkRegist(Model model) {
		
		// 숙제 등록 페이지 속 숙제 목록 보기
		ArrayList<HomeWorkVO> list = teacherService.getHomework();
		model.addAttribute("list", list);
		
		return "teacher/homeWorkRegist";
	}
	
	// 숙제 전송
	@GetMapping("/homeWorkTransfer")
	public String homeWorkTransfer(HttpServletRequest request,
									Model model) {
		// 세션값 받아오기
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		
		// 내가 만든 숙제 조회
		ArrayList<HomeWorkVO> list = teacherService.getMyHomework(String.valueOf(vo.user_no));
		model.addAttribute("homework", list);
		
		// 내가 만든 그룹 조회
		
		
		
		return "teacher/homeWorkTransfer";
	}
	
	// 숙제 제출 조회
	@GetMapping("/homeWorkScore")
	public String homeWorkScore(HttpServletRequest request,
								Model model) {
		
		// 세션값 받아오기
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("vo");
		
		// 내가 만든 숙제 조회
		ArrayList<HomeWorkVO> list = teacherService.getMyHomework(String.valueOf(vo.user_no));
		model.addAttribute("homework", list);
		
		// 숙제 제출한 사람들 가져오기
		
		
		
		return "teacher/homeWorkScore";
	}
	
	
	// 학습 그룹 등록 페이지
	@GetMapping("/learnGroupRegist")
	public String learnGroupRegist(Model model, HttpServletRequest request) {
		
		// 이 작업들은, 내가 구매한 학습컨텐츠 가져오려고 하는거임
		HttpSession session = request.getSession();
		UserVO vo =(UserVO) session.getAttribute("vo");
		int user_no = Integer.parseInt(String.valueOf(vo.user_no));
		
		// 이미지 포함
		ArrayList<PurchaseVO> purListWithImg = cartService.purchaseHistoryWithImg(user_no);
		model.addAttribute("listWithImg" , purListWithImg);
		
		
		return "teacher/learnGroupRegist";
	}
	
	// 학습 그룹 등록 폼
	// 여기 안에 GAME_NO는 나중에 값 직접 받아와야 함(나중에 게임상품 등록 만들면 수정)
	@PostMapping("/learnGroupRegistForm")
	public String learnGroupRegistForm(learnGroupVO vo) {

		System.out.println("------------------------------------");
		System.out.println(vo);
		teacherService.RegistGroup(vo);
		return "teacher/main";
	}
	
	// 학습 그룹 조회
	@GetMapping("/learnGroupLook")
	public String learnGroupLook(Model model, HttpServletRequest request) {
		// 이건 만약에 세션값 받아오고 싶으면
//		HttpSession session = request.getSession();
//		UserVO vo = (UserVO)session.getAttribute("vo");

		
		ArrayList<learnGroupVO> list = teacherService.learnGroupLook();
		model.addAttribute("list", list);
		
		System.out.println(list);
		
		return "teacher/learnGroupLook";
	}
	
	// 숙제 등록
	@PostMapping("/homeWorkRegistForm")
	public String homeWorkRegistForm(HomeWorkVO vo) {
		
		System.out.println("으아아아앙아ㅏ아아아아아아아ㅏㄱ");
		System.out.println("으아아아앙아ㅏ아아아아아아아ㅏㄱ");
		System.out.println("으아아아앙아ㅏ아아아아아아아ㅏㄱ");
		System.out.println("으아아아앙아ㅏ아아아아아아아ㅏㄱ");
		System.out.println("으아아아앙아ㅏ아아아아아아아ㅏㄱ");
		
		System.out.println(vo);
		teacherService.RegistHomework(vo);
		
		return "teacher/main";
	}
	
	// 그룹 가입 상세 조회
	@GetMapping("/groupRegistLook")
	public String groupRegistLook(Model model,
								  @RequestParam("sg_no") int sg_no) {
		
		System.out.println("그룹상세조회, sg_no 넘어옴" + sg_no);
		
		learnGroupVO vo = teacherService.groupDetail(sg_no);
		
		model.addAttribute("group", vo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("groupdetail", teacherService.groupDetail(sg_no));
		map.put("boys", teacherService.mygroupguys(sg_no));
		model.addAttribute("info", map);

		
		return "teacher/groupRegistLook";
	}
	
	// 학습 그룹 승인
	@GetMapping("/groupRegistApprove")
	public String groupRegistApprove() {
		
		
		
		return "teacher/groupRegistApprove";
	}
	
	
	@GetMapping("/detailStudentLook")
	public String detailStudentLook() {
		
		return "teacher/detailStudentLook";
	}
	
	@GetMapping("/myproductPopup")
	public String myproductPopup(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		UserVO vo =(UserVO) session.getAttribute("vo");
		int user_no = vo.user_no;
		
		// 이미지 포함
		ArrayList<PurchaseVO> purListWithImg = cartService.purchaseHistoryWithImg(user_no);
		model.addAttribute("listWithImg" , purListWithImg);
		
		
		
		return "teacher/myproductPopup";
	}
	
	
	
}
