package com.geomin.project.controller;

import java.util.ArrayList;
import java.util.List;

import com.geomin.project.board.service.BoardService;
import com.geomin.project.command.QnaVO;
import com.geomin.project.command.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geomin.project.UserServiceTwo.UserServiceTwo;
import com.geomin.project.command.GameContentVO;
import com.geomin.project.service.command.CommandService;
import com.geomin.project.util.JCriteria;
import com.geomin.project.util.JPageVO;
import com.geomin.project.util.JTwoCriteria;
import com.geomin.project.util.JTwoPageVO;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userServiceTwo")
	public UserServiceTwo userServiceTwo;

	@Autowired
	@Qualifier("commandService")
	public CommandService commandService;

	@Autowired
	@Qualifier("BoardService")
	public BoardService boardService;

	@GetMapping("/main")
	public String main(Model model, HttpSession session) {
		UserVO vo = (UserVO) session.getAttribute("vo");
		model.addAttribute("user",vo);
		ArrayList<QnaVO> getQna_two = boardService.getQna_two();
		model.addAttribute("getQna_two", getQna_two );
		return "user/main";
	}

	@GetMapping("/Study_Status")
	public String Study_Status(Model model, JTwoCriteria JTwo) {
		JTwoPageVO JTwoPageVO = new JTwoPageVO(JTwo, 0);
		model.addAttribute("JTwoPageVO", JTwoPageVO);
		return "user/Study_Status";
	}

	@GetMapping("/study_fun")
	public String SubScribe() {
		return "user/study_fun";
	}
	
	
	
	@GetMapping("/study_fun2")
	public String SubScribe2() {
		return "user/study_fun2";
	}

	@GetMapping("/User_myproduct")
	public String Usermyproduct(Model model, JTwoCriteria JTwo) {

		ArrayList<GameContentVO> List = userServiceTwo.MyList(JTwo);
		int JTwoTotal = userServiceTwo.JTwoTotal(JTwo);
		JTwoPageVO JTwoPageVO = new JTwoPageVO(JTwo, JTwoTotal);
		model.addAttribute("JTwoPageVO", JTwoPageVO);
		model.addAttribute("List", List);

		// 구매이력의 페이지당 포함될수 있는 리스트 양이 다르기 때문에 (JPageVO, JCriteria)한 묶음으로 한개 (JTwoPageVO,
		// JTwocriteria)한 묶음으로 한개씩 두개를 만들었습니다.
		System.out.println(List);
		System.out.println(JTwoPageVO);
		System.out.println(JTwoTotal);
		return "user/User_myproduct";
	}

	// 이것이 나머지 리스트들을 구하는것
	public static List<GameContentVO> safeList(List<GameContentVO> List, int fromIndex, int toIndex) {
		int actualToIndex = Math.min(List.size(), toIndex);
		if (fromIndex >= List.size()) {
			return new ArrayList<>();

		}
		return List.subList(fromIndex, actualToIndex);
	}

	@GetMapping("SearchForm")
	public String googoo(Model model) {
		JTwoPageVO JTwoPageVO = new JTwoPageVO();
		// JTwoPageVO 객체 초기화 로직 (예: jTwoPageVO.setJTwoAmount(값 설정);)

		model.addAttribute("JTwoPageVO", JTwoPageVO);
		return "user/User_myproduct";

	}

	@GetMapping("/buketgameList")
	public String power(Model model, JCriteria JCri) {
		ArrayList<GameContentVO> list = commandService.getList(JCri);
		List<GameContentVO> pagesubList = safeList(list, 0, 5);

		List<GameContentVO> pagesubListTwo = safeList(list, 5, 10);
		int total = commandService.getTotal(JCri);
		JPageVO JPageVO = new JPageVO(JCri, total);

		model.addAttribute("JPageVO", JPageVO);
		model.addAttribute("pagesubList", pagesubList);
		model.addAttribute("pagesubListTwo", pagesubListTwo);
		return "user/buketgameList";
	}

	public static List<GameContentVO> safeList1(List<GameContentVO> list, int fromIndex, int toIndex) {
		int actualToIndex = Math.min(list.size(), toIndex);
		if (fromIndex >= list.size()) {
			return new ArrayList<>();

		}
		return list.subList(fromIndex, actualToIndex);
	}

	
}