package com.example.project_wizian2.command;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeVO {
	private Integer resume_num;
	private String title;
	private String stu_name;
	private String stu_address;
	private String stu_address_detail;
	private String stu_phone;
	private String stu_email;
	private String stu_age;
	private String stu_certi_name;
	private String stu_gradyn;
	private String stu_jobstartdate;
	private String stu_jobenddate;
	private String stu_jobcompname;
	private String stu_joblevel;
	private String stu_jobjob;
	private String stu_jobwork;
	private String stu_awardname;
	private String stu_awardaward;
	private String stu_awardcompname;
	private String stu_emp_type;
	private String stu_interest;
	private String stu_wage;
	private String stu_yn;
	private String stu_id;
	private String user_id;

}
