package com.geomin.project.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseVO {

	private Integer game_no;
	private Integer user_no;
	private String purchase_date;
	
	//게임 콘텐츠
	private String game_title;
	private String game_content;
	private Integer game_price;
	
	//게임 사진
	private String filename;
	private String filepath;
	private String uuid;
	
	//매출 상세 조회
	private String user_name;
	private Integer game_discount_price;
	
	
}
