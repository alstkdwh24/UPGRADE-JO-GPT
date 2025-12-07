package com.example.project_wizian2.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.project_wizian2.util.Criteria;

import lombok.Data;
@Data
public class PageVO {
	private int start;//페이지 네이션 시작번호
	private int end;//페이지네이션 끝번호
	private boolean prev; //이전버튼 활성화여부
	private boolean next; //다음버튼 활성화여부
	
	private int page; //현재 조회하는 페이지 번호(criteria가 가지고 있음)
	private int amount; //조회하는  데이터 갯수(criteria 가 가지고 있음)
	private int total; //전체 데이터 갯수
	
	private int realEnd; //진짜 끝번호
	
	
	private Criteria cri; //페이지 기준
	
	private List<Integer>pageList; //페이지
	
	//생성자 - 계산에 필요함
	public PageVO(Criteria cri, int total) {
		//생성될 때, 페이지 번호, 데이터 갯수, 젠체개시글수 초기값지전
		this.page  = cri.getPage();
		this.amount = cri.getAmount();
		this.total =total;
		this.cri=cri;
		
		//끝 페이지 계산 - 현제 조회하는 페이지에 따라 변함
		//ex) page 11이면, 끝 페이지는 20
		//ex) page 1이면, 끝 페이지는 10
		//ex) page 31이면, 끝 페이지는 40
		this.end =(int)(Math.ceil(this.page/10.0))*10;//페이지네이션 갯수
		
		//시작페이지 계산
		//끝페이지 - 페이지네이션수 +1
		this.start = this.end -10+1;
		
		//실제 마지막 페이지 재계산 -realEnd
		//만약에 총게시글이 53개라면? -> 끝페이지 계산시 10, 실제 끝번호는 6
		//만약에 총게시글수가 163개라면?->끝페이지 계산시 20, 실제 끝번호는 17
		//만약애 총게시글수가 163개인데, 1~100번 데이터를 조회하는 경우 ->끝페이지 10
		//(int) 올림(총게시글수 /화면에 뿌려지는 데이터 갯수)
		this.realEnd = (int)(Math.ceil(this.total/(double)this.amount)); 
	
	
		//끝페이지 다시결정
		//데이터 갯수 163개
		//1~10페이지 조회 시에는,끝페이지 번호 10, 진짜 끝번호는 17
		//11~20페이지 조회시에는, 끝패이지 번호 20, 진짜 끝번호는 17
		if(this.end>this.realEnd) {
			this.end = this.realEnd;
		}
		//이전버튼 활성화 여부
		//start는 1, 11, 21, 31........
	    this.prev =this.start > 1;//1보다 크면 true
	    
	    //다음 버튼 활성화 여부- 끝페이지 다시 결정과 연관이 있음
	     this.next = this.realEnd > this.end;
	     
	     //타임리프는 향상된 for문 밖에 지원이 안되서 ,패이지 번호를 들고있는 item을 형성함
	     this.pageList=IntStream.rangeClosed(this.start,this.end)
	    		 .boxed()
	    		  .collect(Collectors.toList());
	}
}
