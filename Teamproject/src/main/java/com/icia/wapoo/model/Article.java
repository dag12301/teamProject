package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article{
	private int articleId;		// 게시물 PK
	private String regDate;			// 날짜
	private String updateDate;		// 수정 날짜
	private String title;			// 제목
	private String body;			// 내용
	private int hit;				// 조회수
	private String status;			// 상태
	private int parant_id;			// 부모 게시글
	private int board_id;			// 게시판 종류
	private int writer_id;			// 작성자

}
