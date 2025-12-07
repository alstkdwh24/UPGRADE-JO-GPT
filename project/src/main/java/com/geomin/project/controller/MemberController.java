package com.geomin.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geomin.project.command.UserVO;
import com.geomin.project.security.MyUserDetails;
import com.geomin.project.security.MyUserDetailsService;
import com.geomin.project.security.UpdateUserService;
import com.geomin.project.user.service.UserService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private UpdateUserService updateUserService;
	
	@Autowired
	private UserService userService;
	
	// 회원가입 - 화면 접속
	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
	
	// 회원가입 - 기능 수행
	@PostMapping("/joinForm")
	public String joinForm(UserVO vo) {

		
		String pw = bCryptPasswordEncoder.encode(vo.getUser_pw());
		vo.setUser_pw(pw);
		
		
		System.out.println(pw);
		userService.regist(vo);
		
		return "redirect:/main";
	}
	
	// 로그인 - security 적용
	@GetMapping("/login")
	public String login(@RequestParam(value="err", required=false) String err,
			Model model) {

		if(err != null) {
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
		}
		
		return "member/login";
	}
	
	// 회원정보 수정
	@PostMapping("/updateForm")
	public String updateForm(UserVO vo) {

		userService.modify(vo);
		updateUserService.updateUserInformation(vo);
		System.out.println("업데이트 성공 !! : " + vo.toString());
		return "redirect:/command/modify";
	}
	
}
