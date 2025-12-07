package com.geomin.project.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVO {

	private int user_no;
	
	
	//그룹 신청
	private int sg_no;
	private int sg_level;
	
}
