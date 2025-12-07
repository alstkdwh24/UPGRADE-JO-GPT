package com.geomin.project.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geomin.project.board.service.BoardService;
import com.geomin.project.command.GameContentVO;
import com.geomin.project.command.HomeWorkVO;
import com.geomin.project.command.PageVO;
import com.geomin.project.command.ProgressVO;
import com.geomin.project.command.QnaVO;
import com.geomin.project.command.StudyGroupVO;
import com.geomin.project.command.UserVO;
import com.geomin.project.command.learnGroupVO;
import com.geomin.project.gameContentService.GameContentService;
import com.geomin.project.student.service.StudentMapper;
import com.geomin.project.student.service.StudentService;
import com.geomin.project.teacher.service.TeacherService;
import com.geomin.project.user.service.UserService;
import com.geomin.project.util.Criteria;
import com.geomin.project.util.CriteriaMember;
import com.geomin.project.util.StudyGroupCriteria;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	TeacherService teacherService;

	@Autowired
	StudentService studentService;

	@Autowired
	BoardService boardService;
	
	@Autowired
	UserService userService;

	@Autowired
	GameContentService gameContentService;

	@GetMapping("/main")
	public String main(Model model, Criteria criteria, HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		model.addAttribute("vo", vo);
		System.out.println(vo.toString());
		ArrayList<QnaVO> qnaList = boardService.getQna();
		model.addAttribute("qnaList", qnaList);

		ArrayList<GameContentVO> list = gameContentService.getList(criteria);
		model.addAttribute("gameContent", list);

		return "student/main";
	}

	@GetMapping("/game")
	public String game() {
		return "student/game";
	}

	@GetMapping("/groupStudyList")
	public String groupStudyList(Model model, HttpServletRequest request, StudyGroupCriteria cri) {

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		int user_no = vo.user_no;

		// 그룹스터디 내용 리스트
		ArrayList<StudyGroupVO> list = studentService.getList(cri);
		model.addAttribute("list", list);

		// 페이지네이션
		int total = studentService.getTotal();
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("pageVO", pageVO);

		return "student/groupStudyList";
	}

	// 숙제 리스트 받아오기
	@GetMapping("/homeworkList")
	public String homeworkList(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		int user_no = vo.user_no;
		ArrayList<HomeWorkVO> hwList = studentService.getHomeworkList(user_no);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date currentTime = new Date();
		String date = format.format(currentTime);

		model.addAttribute("user_name", vo.user_name);
		model.addAttribute("hwList", hwList);
		model.addAttribute("date", date);

		return "student/homeworkList";
	}

	@GetMapping("/homeworkDetail")
	public String homeworkDetail(Model model, HttpServletRequest request,
			@RequestParam("homework_no") int homework_no) {

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		int user_no = vo.user_no;
		ArrayList<HomeWorkVO> hwList = studentService.getHomeworkDetail(user_no, homework_no);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date currentTime = new Date();
		String date = format.format(currentTime);
		System.out.println(hwList.toString());

		model.addAttribute("user_name", vo.user_name);
		model.addAttribute("hwList", hwList);
		model.addAttribute("date", date);

		return "student/homeworkDetail";
	}

	// 그룹 가입 상세 조회
	@GetMapping("/groupApplyList")
	public String groupRegistLook(Model model, HttpServletRequest request, @RequestParam("sg_no") int sg_no) {

		HttpSession session = request.getSession();
		UserVO Uservo = (UserVO) session.getAttribute("vo");
		int user_no = Uservo.user_no;

		ArrayList<StudyGroupVO> list = studentService.groupList(user_no, sg_no);

		learnGroupVO vo = teacherService.groupDetail(sg_no);
		if (vo != null) {
			model.addAttribute("group", vo);

			Map<String, Object> map = new HashMap<>();
			map.put("groupdetail", teacherService.groupDetail(sg_no));
			model.addAttribute("info", map);
		}
		if (!list.isEmpty()) {
			String auth = list.get(0).sgj_auth;
			System.out.println(auth);
			model.addAttribute("auth", auth);
			model.addAttribute("list", list);
		}

		return "student/groupApplyList";
	}

	// 그룹 가입 신청 승인 여부
	@GetMapping("/groupApproval")
	public String groupApprove(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		int user_no = vo.user_no;

		ArrayList<StudyGroupVO> sgList = studentService.groupApplyList(user_no);

		model.addAttribute("sgList", sgList);
		System.out.println(sgList.toString());
		return "student/groupApproval";
	}

	// 나의 그룹
	@GetMapping("/myGroupList")
	public String myGroupList(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		int user_no = vo.user_no;

		ArrayList<StudyGroupVO> sgList = studentService.groupApplyList(user_no);
		System.out.println(sgList.toString());
		if(!sgList.isEmpty()) {
			for (StudyGroupVO SGvo : sgList) {
			    int sg_no = Integer.parseInt(SGvo.getSg_no());
			    System.out.println("1번 동작함");
			    int classProgress = studentService.getClassProgress(sg_no);
			    System.out.println("2번 동작함");
		    	
			    try {
			    	int homework_total_point = studentService.totalHomeworkPoint(user_no, sg_no);
			    	double percentage = ((double) homework_total_point / classProgress) * 100; 
			    	int final_percentage = (int) Math.ceil(percentage);
			    	System.out.println(final_percentage);
			    	SGvo.setClass_progress(final_percentage);
			    	model.addAttribute("sgList", SGvo);
					
				} catch (Exception e) {
					System.err.println("에러남");
				}
			    
			    model.addAttribute("sgList", SGvo);

			}		
			model.addAttribute("sgList", sgList);
		}
		
		return "student/myGroupList";
	}

	// 숙제 내역 조회
	@GetMapping("/homeworkTable")
	public String homeworkTable(Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		int user_no =vo.user_no;
		ArrayList<HomeWorkVO> hwList = studentService.getHomeworkList(user_no);

		model.addAttribute("user_name", vo.user_name);
		model.addAttribute("hwList", hwList);

		if (hwList.isEmpty()) {
			System.out.println("Homework list is empty");
			return "student/homeworkTable";
		}

		// 날짜 오늘 구해서 D-Day 구하기
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate today = LocalDate.now();

		for (HomeWorkVO hw : hwList) {
			LocalDate dueDate = LocalDate.parse(hw.getHomework_duedate(), formatter);
			long homework_leftdate = ChronoUnit.DAYS.between(today, dueDate);
			System.out.println(
					"homework_No: " + hw.getHomework_no() + ", 남은 날짜: " + homework_leftdate);
			
			Integer teachGrade = hw.getTeach_grade();
			int homework_no = Integer.parseInt(hw.getHomework_no());
			Integer homework_point = hw.getHomework_point();
			System.out.println(homework_point);
			Integer sg_no = Integer.parseInt(hw.getSg_no());

			if(teachGrade != null && teachGrade >=2 && homework_point == null) {
				studentService.addPoint(user_no, homework_no);
				System.out.println(sg_no + "~~~~~~~~~~~~~~~~~~~~~~~~");
				studentService.insertClassProgress(user_no, sg_no, homework_no);
			}else if(teachGrade != null && teachGrade >= 2) {
		    	int sumPoint = studentService.sumPoint(user_no, sg_no);
		    	
			}else if(teachGrade == null || homework_point == null || sg_no == null) {
				System.err.println("에러에러");
			}

			studentService.leftDate(homework_leftdate, user_no, Integer.parseInt(hw.getHomework_no()));
		}

		return "student/homeworkTable";
	}

	// 슥제 제출
	@GetMapping("/submission")
	public String submission(HomeWorkVO hwVO) {
		studentService.homeworkSubmission(hwVO);
		System.out.println(hwVO.toString());
		return "redirect:/student/homeworkTable";
	}

	@GetMapping("/homeworkPass")
	public String homeworkPass() {

		return "student/homeworkPass";
	}

	@GetMapping("/myGroupDetail")
	public String myGroupDetail(Model model, HttpServletRequest request, @RequestParam("sg_no") int sg_no, CriteriaMember cri) {

		HttpSession session = request.getSession();
		UserVO Uservo = (UserVO) session.getAttribute("vo");
		int user_no = Uservo.user_no;

		ArrayList<StudyGroupVO> list = studentService.groupList(user_no, sg_no);
		ArrayList<ProgressVO> progressList = studentService.allStudentPointList(sg_no);
		
		int classProgress = studentService.getClassProgress(sg_no);
		model.addAttribute("classProgress", classProgress);
		

		learnGroupVO vo = teacherService.groupDetail(sg_no);
		model.addAttribute("group", vo);
		
		System.out.println("list: " + list.toString());
		System.out.println("progressList: " + progressList.toString());

		if (!list.isEmpty() || !progressList.isEmpty()) {
			
			String auth = list.get(0).sgj_auth;
			//System.out.println(auth);
			model.addAttribute("auth", auth);
			model.addAttribute("list", list);

			 List<ProgressVO> updatedProgressList = new ArrayList<>();
			    
			    for (ProgressVO progress : progressList) {
			        int total_point = progress.getHomework_total_point();
			        double percentage = ((double) total_point / classProgress) * 100;
			        int final_percentage = (int) Math.ceil(percentage);

			        progress.setProgress_percentage(final_percentage);

			        updatedProgressList.add(progress);
			    }
			    
			    model.addAttribute("progressList", updatedProgressList);
		}
		

		return "student/myGroupDetail";
	}

	@GetMapping("/playGo")
	public String playGo() {

		return "student/playGo";
	}

}
