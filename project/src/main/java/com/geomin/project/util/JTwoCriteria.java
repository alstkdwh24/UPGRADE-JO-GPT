package com.geomin.project.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

 // 여기는 페이지하고 페이지에 나오는 리스트 량을 설정하는 클래스
public class JTwoCriteria {
    private int JTwoPage;
    private int JTwoAmount;
    private Integer game_no; // 여기에 없다고 오류가 떠서 추가함
    private String searchName; // 상품이름
    private String searchContent; // 상품내용
    private String searchPrice; // 상품가격 정렬방식
     // 날짜조회 시작
    // 날짜조회 끝
    private String pageVOVO;
    private String searchNa;
    private String searchtitle;
    private String search_product;
    private String startDate;
    private String endDate;
    private Integer user_no;



    private int good=(JTwoPage - 1) * JTwoAmount;


    // 기본 생성자를 통한 초기화
    public JTwoCriteria() {

        this.JTwoPage = 1;
        this.JTwoAmount = 4; // 예시로 10으로 설정

    }

    // 페이지와 리스트 량을 설정하는 생성자
    public JTwoCriteria(int JTwoPage, int JTwoAmount,String startDate,  String endDate ,int good) {
        this(); // 기본 생성자 호출로 중복 코드 제거
        this.JTwoPage = JTwoPage;
        this.JTwoAmount = JTwoAmount;
        this.endDate=endDate;
        this.startDate=startDate;

    }
    public LocalDate getStartDates() {
        if (startDate != null) {
            DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(startDate, format1);
        }
        return null;
    }

    public LocalDate getEndDatee() {
        if (endDate != null) {
            DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(endDate, format2);
        }
        return null;
    }
    public int getJTwoPageFirst() {
        return (JTwoPage - 1) * JTwoAmount;
    }

    public int getJTwoAmount() {
        return JTwoAmount;
    }


}

