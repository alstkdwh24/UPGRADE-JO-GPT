package com.geomin.project.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {

	private int notice_no;
	private int notice_type;
	private int notice_check;
	private String notice_title;
	private String notice_dueDate;
	private String notice_content;
	private String notice_delCheck;
	
}
