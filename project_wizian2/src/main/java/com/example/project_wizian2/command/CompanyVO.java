package com.example.project_wizian2.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyVO {
	
	private String com_id;
	private String com_pw;
	private String com_name;
	private String com_department;
	private String com_phone;
	private String com_email;
	private String com_type;
	private String com_auth;
	
}
