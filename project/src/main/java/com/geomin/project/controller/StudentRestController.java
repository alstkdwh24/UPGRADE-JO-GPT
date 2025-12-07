package com.geomin.project.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geomin.project.command.StudyGroupVO;
import com.geomin.project.command.UserVO;
import com.geomin.project.student.service.StudentMapper;
import com.geomin.project.student.service.StudentService;

@RestController
public class StudentRestController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("apply/group")
	public int groupApply(@RequestParam("user_no") int user_no,
						   @RequestParam("sg_no") int sg_no,
						   @RequestParam("sg_level") int sg_level) {
	
		return studentService.groupApply(user_no, sg_no, sg_level);
	}
	
	@GetMapping("check/group")
	public boolean groupCheck(@RequestParam("user_no") int user_no,
						  @RequestParam("sg_no") int sg_no) {
		
		
		int inGroup = studentService.groupCheck(user_no, sg_no);
        return inGroup > 0;
	}
	@GetMapping("reject/group")
	public Boolean rejectCheck(@RequestParam("user_no") int user_no, @RequestParam("sg_no") int sg_no) {
	   
		ArrayList<StudyGroupVO> list = studentService.rejectCheck(user_no, sg_no);
		if(!list.isEmpty()) {
			int auth = Integer.parseInt(list.get(0).getSgj_auth());
            return auth == 2;
		}
		
		return false;
	
	}
	
	@GetMapping("approve/group")
	public boolean approveCheck(@RequestParam("user_no") int user_no,
						  @RequestParam("sg_no") int sg_no) {
		
		ArrayList<StudyGroupVO> list = studentService.groupList(user_no, sg_no);
		int auth = Integer.parseInt( list.get(0).sgj_auth);

        return auth != 1;
		
	}
	
	@GetMapping("reapply/group")
	public int reapply(@RequestParam("user_no") int user_no,
						@RequestParam("sg_no") int sg_no) {
		
		int a = studentService.reapplyGroup(user_no, sg_no);
		System.out.println(a);
		return a;
	}
	
	@GetMapping("/check/ai")
	public ArrayList<StudyGroupVO> checkAi(@RequestParam("user_no") int user_no,
											HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("vo");
		int user_level = Integer.parseInt( vo.user_level );
		System.out.println(user_level);
		System.out.println(user_no);

		ArrayList<StudyGroupVO> list =studentService.aiList(user_no, user_level);
		
		System.out.println(list.toString());
		
		return studentService.aiList(user_no, user_level);
	}

	/*
	 * @GetMapping("/group/search") 
	 * public
	 */
	
}
