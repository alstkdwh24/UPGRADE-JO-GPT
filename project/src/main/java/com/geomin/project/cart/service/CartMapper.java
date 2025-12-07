package com.geomin.project.cart.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.geomin.project.command.CartVO;
import com.geomin.project.command.PurchaseVO;

@Mapper
public interface CartMapper {
	
	//장바구니 추가
    int addtoCart(@Param("user_no") int user_no, @Param("game_no") int game_no);
	
	//장바구니 중복 조회
    int checkCart(@Param("user_no") int user_no, @Param("game_no") int game_no);
	
	//장바구니 조회
    ArrayList<CartVO> getListCart(int user_no);
	
	//장바구니 결제 완료
    int successPay(@Param("user_no") int user_no);
	
	//장바구니 삭제
    int delCart(@Param("user_no") int user_no, @Param("game_no") int game_no);
	
	//결제 내역 추가
    int gamePurchase(@Param("user_no") int user_no, @Param("game_no") int game_no);
	
	//결재 내용 체크
    int checkPurchase(@Param("user_no") int user_no, @Param("game_no") int game_no);
	
	//구매 리스트
    ArrayList<PurchaseVO> purchaseHistory(@Param("user_no") int user_no);
	
	// 구매 이력 리스트인데, 이미지 추가버전
    ArrayList<PurchaseVO> purchaseHistoryWithImg(int user_no);


    int purchase_resist(PurchaseVO vo);


}
