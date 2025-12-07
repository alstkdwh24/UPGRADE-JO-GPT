package com.geomin.project.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaqVO {

	private int faq_no;
	private int faq_type;
	private String faq_question;
	private String faq_answer;
	private String faq_regDate;
	private String faq_delCheck;
}
