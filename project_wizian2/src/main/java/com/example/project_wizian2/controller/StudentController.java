package com.example.project_wizian2.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project_wizian2.command.JobVO;
import com.example.project_wizian2.command.ManagerResumeListVO;
import com.example.project_wizian2.command.ResumeVO;
import com.example.project_wizian2.manager.service.ManagerService;
import com.example.project_wizian2.resume.service.ResumeService;

@Controller
@RequestMapping("/user_stu")
public class StudentController {
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private ManagerService managerService;
	
	
	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String auth = (String) session.getAttribute("user_id");

		return "/user_stu/home";
	}
	
	@GetMapping("/myhome_stu")
	public String myhome_stu(Model model, String stu_id, HttpSession session) {
		
		stu_id = (String)session.getAttribute("user_id");
		
		ArrayList<ManagerResumeListVO> updatePost = managerService.updatePost();
		model.addAttribute("updatePost", updatePost);
		
		ArrayList<JobVO> list = managerService.applyList(stu_id);
		model.addAttribute("list", list);
		
		return "/user_stu/myhome_stu";
	}
	
	// 학생 입장에서 보는 채용공고
	@GetMapping("/notice_stu")
	public String notice_stu(Model model) {

		ArrayList<ManagerResumeListVO> list = managerService.updatePost();
		model.addAttribute("list", list);
		return "/user_stu/notice_stu";
	}
	
	@GetMapping("/applyList_stu")
	public String applyList_stu(String stu_id, Model model, HttpSession session) {
		

		stu_id = (String)session.getAttribute("user_id");
		
		ArrayList<JobVO> vo = managerService.applyList(stu_id);
		
		model.addAttribute("vo", vo);
		
		return "/user_stu/applyList_stu";

	}
	
	
	@GetMapping("/recHire_stu")
	public String recHire_stu() {
		return "/user_stu/recHire_stu";
	}
	
	@GetMapping("/resume_supportCenter")
	public String resume_supportCenter() {
		
		
		return "/user_stu/resume_supportCenter";
	}
	
	@PostMapping("/resume_supportCenterForm") 
	public String resume_supportCenterForm(ResumeVO vo) {
		
		resumeService.registResume(vo);
		

		return "redirect:/user_stu/myhome_stu";

	}
	

	
	
	
}
