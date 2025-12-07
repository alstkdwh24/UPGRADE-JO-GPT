package com.geomin.project.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyGroupVO {
	
	public String sg_no;
	public String user_no;
	public String game_no;
	public String sg_name;
	public String sg_level;
	public String sg_capa;
	public String sg_regdate;
	public String sg_enddate;
	public String sg_plus;
	public String sg_grouplimit;
	public Integer sg_class;
	
	//스터디 그룹 승인 절차
	public String sgj_auth;
	public String sgj_target_date;
	
	//GAME_CONTENT 내용
	public String game_title;
	
	//최종 진도율
	public Integer class_progress;
}
