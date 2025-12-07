package com.example.project_wizian2.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project_wizian2.command.CompanyVO;
import com.example.project_wizian2.command.ManagerVO;
import com.example.project_wizian2.command.StudentVO;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper usermapper;
	
	@Override
	public int stu_regist(StudentVO vo) {
		
		int result = usermapper.stu_regist(vo);
		
		return result;
	}

	@Override
	public int com_regist(CompanyVO vo) {
		return usermapper.com_regist(vo);
	}

	@Override
	public int man_regist(ManagerVO vo) {
		return usermapper.man_regist(vo);
	}

	@Override
	public StudentVO stu_idcheck(StudentVO vo) {
		return usermapper.stu_idcheck(vo);
	}

	@Override
	public CompanyVO com_idcheck(CompanyVO vo) {
		return usermapper.com_idcheck(vo);
	}

	@Override
	public ManagerVO man_idcheck(ManagerVO vo) {
		return usermapper.man_idcheck(vo);
	}


	

}
