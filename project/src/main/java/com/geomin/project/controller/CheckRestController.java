package com.geomin.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.geomin.project.command.HomeworkHistoryVO;
import com.geomin.project.command.learnGroupVO;
import com.geomin.project.teacher.service.TeacherService;
import com.geomin.project.user.service.UserService;


@RestController
public class CheckRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TeacherService teacherService;
	
	// 회원가입 페이지 아이디체크
	// 배포 시, CrossOrigin 설정 다시 해야함
	@GetMapping("/check/{user_id}")
	public int idCheck(@PathVariable("user_id") String id) {
		
		return userService.buttonIdCheck(id);
	}
	
	// 숙제 페이지에 학습그룹 불러오기
	@GetMapping("/mygroup/{user_no}")
	public ArrayList<learnGroupVO> myGroupList(@PathVariable("user_no") int user_no) {
		
		
		return teacherService.myGroupList(user_no);
	}
	
	// 그룹 가입 승인 페이지에서, 디테일 가져오는거
	@GetMapping("/groupDetails/{SG_NO}")
	public learnGroupVO groupDetails(@PathVariable("SG_NO") int sg_no) {
		
		Map<String, Object> map = new HashMap<>();
		
		// 그룹 데이터
		map.put("um1", teacherService.groupDetail(sg_no));
		// 그룹 속 학생데이터
		map.put("um", teacherService.groupDetail2(sg_no));
		

		return teacherService.groupDetail(sg_no);

	}
	
	// 그룹 가입 승인 페이지에서, 디테일 가져오는거
	@GetMapping("/groupDetail/{SG_NO}")
	public Map<String, Object> groupDetail(@PathVariable("SG_NO") int sg_no) {
		
		Map<String, Object> map = new HashMap<>();
		
		// 그룹 가져오기
		map.put("groupdetail", teacherService.groupDetail(sg_no));
		// 그룹에 신청한 학생들 가져오기
		map.put("boys", teacherService.groupDetail2(sg_no));
		
		return map;
	}
	
	// 그룹 가입 승인
	@GetMapping("/approve/{sg_no}/{user_no}")
	public int approve(@PathVariable("user_no") int user_no,
	 		  		   @PathVariable("sg_no") int sg_no) {
		
		int result = teacherService.approve(sg_no, user_no);
		
		return result;
	}
	
	// 그룹 가입 승인 시, capacity + 1
	@GetMapping("/groupcapa/{sg_no}")
	public int groupcapa(@PathVariable("sg_no") int sg_no) {
		
		
		return teacherService.capacity(sg_no);
	}
	
	// 그룹 가입 거절
	@GetMapping("/reject/{sg_no}/{user_no}")
	public int reject(@PathVariable("user_no") int user_no,
			 		  @PathVariable("sg_no") int sg_no) {
		
		
		System.out.println("유저넘버: " + user_no);
		System.out.println("그룹넘버: " + sg_no);
		
		return teacherService.reject(sg_no, user_no);
	}
	
//	// 그룹 가입 거절 시 인구수 -1
//	@GetMapping("/groupcapaminus/{sg_no}")
//	public int groupcapaminus(@PathVariable("sg_no") int sg_no) {
//		
//		return teacherService.capaMinus(sg_no);
//	}
//	
	
	// 승인된 그룹자들 가져오기
	@GetMapping("/mygroupguys/{sg_no}")
	public ArrayList<learnGroupVO> mygroupguys(@PathVariable("sg_no") int sg_no) {
		
		
		return teacherService.mygroupguys(sg_no);
	}
	
	// 숙제 내주기
	@GetMapping("/homeworkSend/{homework_no}/{user_no}/{sg_no}")
	public int homeworkSend(@PathVariable("homework_no") String homework_no,
								@PathVariable("user_no") String user_no
								,@PathVariable("sg_no") String sg_no) {
		
		
		System.out.println("숙제번호: " + homework_no);
		System.out.println("유저번호: " + user_no);
		System.out.println("그룹번호: " + sg_no);
		
		Map<String, Object> map = new HashMap<>();
		map.put("homework_no", homework_no);
		map.put("user_no", user_no);
		map.put("sg_no", sg_no);
		
		System.out.println(map);
		
		teacherService.homeworkSend(map);
		
		
		return 0;
	}
	
	// 숙제 제출내역 가져오기(제출한 것들만)
	@GetMapping("/homeworkReceive/{homework_no}")
	public ArrayList<HomeworkHistoryVO> homeworkReceive(@PathVariable("homework_no") int homework_no) {
		
		
		return teacherService.homeworkReceive(homework_no);
	}
	
	// 숙제 평가하고 저장버튼 눌렀을 때
	@GetMapping("/homeworkGrade/{homework_no}/{user_no}/{selectedGrade}")
	public int homeworkGrade(@PathVariable("selectedGrade") String selectedGrade,
							 @PathVariable("homework_no") String homework_no,
							 @PathVariable("user_no") String user_no) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("selectedGrade", selectedGrade);
		map.put("homework_no", homework_no);
		map.put("user_no", user_no);
		
		teacherService.homeworkGrade(map);
		
		System.out.println(map);
		
		
		return 0;
	}
	
	public int homeworkProgressInsert (@PathVariable("selectedGrade") String selectedGrade,
	 @PathVariable("homework_no") String homework_no,
	 @PathVariable("user_no") String user_no)  {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("selectedGrade", selectedGrade);
		map.put("homework_no", homework_no);
		map.put("user_no", user_no);
		
		return 0;
		
	}
	
	
	
	
	
	

}
