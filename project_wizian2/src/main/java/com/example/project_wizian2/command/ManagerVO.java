package com.example.project_wizian2.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerVO {
	
	private String man_id;
	private String man_pw;
	private String man_name;
	private String man_department;
	private Integer man_phone;
	private String man_email;
	private String man_auth;
	private String title;

	
}
