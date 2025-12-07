package com.geomin.project.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//페이지 누르는 바 안에 리스트를 만들어주는 기능과 페이지 리스트에 얼마만큼의 페이지를 넣을 건지를 정해주는 클래스
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JPageVO {
	private int JFirst;
	private int JEnd;
	private boolean JPrev;
	private boolean JNext;

	private int JPage;
	private int JAmount;
	private int JTotal;

	private int JRealEnd;

	private JCriteria JCri;

	private List<Integer> JPageList;

	public JPageVO(JCriteria JCri, int Jtotal) {
		this.JPage = JCri.getJPage();
		this.JAmount = JCri.getJAmount();
		this.JTotal = Jtotal;
		this.JCri = JCri;

		this.JEnd = (int) (Math.ceil(this.JPage / 5.0) * 5);

		this.JFirst = this.JEnd - 5 + 1;

		this.JRealEnd = (int) (Math.ceil(this.JTotal / (double) this.JAmount));

		if (this.JEnd > this.JRealEnd) {
			this.JEnd = this.JRealEnd;
		}

		this.JPrev = this.JFirst > 1;

		this.JNext = this.JRealEnd > this.JEnd;

		this.JPageList = IntStream.rangeClosed(this.JFirst, this.JEnd).boxed().collect(Collectors.toList());
	}

	//////////////////////////////////////////////////////////////////////// JTwoCri뭐시기
	//////////////////////////////////////////////////////////////////////// 부분(변한느
	//////////////////////////////////////////////////////////////////////// 변수 부분만
	//////////////////////////////////////////////////////////////////////// 설명하자면
	//////////////////////////////////////////////////////////////////////// JTwoPage,
	//////////////////////////////////////////////////////////////////////// JTwoAmount,
	//////////////////////////////////////////////////////////////////////// JTwoTotal,
	//////////////////////////////////////////////////////////////////////// JTwo)로
	//////////////////////////////////////////////////////////////////////// 변합니다.

}
