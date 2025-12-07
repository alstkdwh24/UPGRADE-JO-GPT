package com.geomin.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geomin.project.cart.service.CartService;
import com.geomin.project.command.CartVO;

@RestController
public class PurchaseController {

	@Autowired
	@Qualifier("CartServiceImpl")
	private CartService cartService;

	@GetMapping("/check/cart")
	public boolean checkCart(@RequestParam("user_no") int user_no, @RequestParam("game_no") int game_no) {
		int inCart = cartService.checkCart(user_no, game_no);

        return inCart <= 0;
	}

	@GetMapping("/add/cart")
	public int addCart(@RequestParam("user_no") int user_no, @RequestParam("game_no") int game_no) {
		return cartService.addtoCart(user_no, game_no);
	}

	@GetMapping("check/purchase")
	public boolean checkPurcahse(@RequestParam("user_no") int user_no, @RequestParam("game_no") int game_no) {
		int purchased = cartService.checkPurchase(user_no, game_no);

        return purchased <= 0;
	}
	

	@GetMapping("/del/cart")
	public void delCart(@RequestParam("user_no") int user_no, @RequestParam("game_no") int game_no) {
		System.out.println(user_no + "님의 " + game_no + "번이 장바구니에서 제거함");
		cartService.delCart(user_no, game_no);
	}

	
	
}
