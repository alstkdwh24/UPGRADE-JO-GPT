package com.geomin.project.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnaVO {
	private int qna_no;
	private int qna_type;
	private int qna_check;
	private String qna_title;
	private String qna_content;
	private String qna_regDate;
	private String qna_delCheck;
	private String alarm;
	
	private int user_no;
}
