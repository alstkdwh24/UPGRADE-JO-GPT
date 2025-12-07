package com.geomin.project.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO {

	private Integer cart_id;
	private Integer user_no;
	private Integer game_no;

	/*GAME_CONTENT에서 CART와 join해서 불러오는 값*/
	private Integer game_price;
	private String game_title;
	private String game_content;
	private Integer game_target_level;
	private Integer game_discount_price;
}
