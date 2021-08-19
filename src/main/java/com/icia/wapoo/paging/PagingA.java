package com.icia.wapoo.paging;

import com.icia.wapoo.model.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingA {
	private int listSize = 10;                //초기값으로 목록개수를 10으로 셋팅

	private int rangeSize = 5;            //초기값으로 페이지범위를 10으로 셋팅

	private int page;						// 현재 페이지

	private int range;						//현재 페이지 범위

	private int total;						// 총 게시물의 개수

	private int pageCnt;					//총 페이지 수

	private int startPage;					//시작번호
	
	private int endPage;					// 각 페이지 범위 끝 번호

	private int startList;					//DB게시판
	
	private int endList;					//DB게시판 끝번호

	private boolean prev;					//이전 페이지

	private boolean next;					//다음페이지
	
	

	public void pageInfo(int page, int range, int total) {

		this.page = page;

		this.range = range;

		this.total = total;

		

		//전체 페이지수 

		this.pageCnt = (int) (Math.ceil(total/listSize) + Math.ceil(total/listSize));

		

		//시작 페이지

		this.startPage = (range - 1) * rangeSize + 1 ;

		

		//끝 페이지

		this.endPage = range * rangeSize;

				

		//DB게시판 시작번호

		this.startList = (page - 1) * listSize;
		//DB게시판 끝번호
		this.endList = page * listSize;

		//이전 버튼 상태

		this.prev = range == 1 ? false : true;

		

		//다음 버튼 상태

		this.next = endPage > pageCnt ? false : true;

		if (this.endPage > this.pageCnt) {

			this.endPage = this.pageCnt;

			this.next = false;

		}

	}

	

	
	
}
