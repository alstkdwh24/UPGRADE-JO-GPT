package com.geomin.project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeworkHistoryVO {
	private String homework_no;
	private String user_no;
	private String teach_assigndate;
	private String stu_content;
	private String stu_q;
	private String stu_duedate;
	private String teach_answer;
	private String teach_geade;
	private String teach_graddate;
	
	
	// user꺼 가져옴
	private String user_name;

}
