package com.example.project_wizian2.user.service;

import com.example.project_wizian2.command.CompanyVO;
import com.example.project_wizian2.command.ManagerVO;
import com.example.project_wizian2.command.StudentVO;

public interface UserService {

	public int stu_regist(StudentVO vo);
	public int com_regist(CompanyVO vo);
	public int man_regist(ManagerVO vo);

	public StudentVO stu_idcheck(StudentVO vo);
	public CompanyVO com_idcheck(CompanyVO vo);
	public ManagerVO man_idcheck(ManagerVO vo);
	
}
