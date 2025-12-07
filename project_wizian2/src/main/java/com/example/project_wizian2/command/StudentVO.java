package com.example.project_wizian2.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentVO {

	private String stu_id;
	private String stu_pw;
	private String stu_name;
	private Integer stu_phone;
	private String stu_email;
	private String stu_auth;
	
}
