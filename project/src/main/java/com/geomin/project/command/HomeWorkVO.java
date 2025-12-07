package com.geomin.project.command;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeWorkVO {
	private String user_no;
	private String homework_no;
	private String homework_name;
	private String homework_content;
	private String homework_level;
	private String homework_duedate;
	private String homework_regdate;
	private String homework_modidate;

	
	
	// 학습그룹 이름, 레벨, 기한 들고옴
	private String sg_name;
	private String sg_level;
	private String user_name;
	
	//Homework_History
	private String stu_content;
	private String stu_q;
	private String teach_assigndate;
	private Integer teach_grade;
	private String teach_answer;
	private String homework_submit;
	private Integer homework_point;
	private Integer homework_leftdate;
	private String sg_no;
	
	public int leftDays() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = sdf.parse(this.homework_regdate);
        Date dueDate = sdf.parse(this.homework_duedate);
        long diff = dueDate.getTime() - regDate.getTime();
		
        return (int) (diff / (24 * 60 * 60 * 1000));
	}
	
	public int hwDuedate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       
        Date regDate = sdf.parse(this.teach_assigndate);
        Date dueDate = sdf.parse(this.homework_duedate);
        long diff = dueDate.getTime() - regDate.getTime();
		
        return (int) (diff / (24 * 60 * 60 * 1000));
	}
	
}
