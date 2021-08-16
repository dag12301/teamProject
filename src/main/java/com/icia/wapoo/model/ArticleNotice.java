package com.icia.wapoo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleNotice implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String articleId;		//게시물 id
	private String regDate;			//날짜
	private String updateDate;		//수정 날짜
	private String title;			//제목
	private String body;			//내용
	private int hit;				//조회수
	private String status;			// 종류
	private int parant_id;			//
	private int board_id;			//
	private int writer_i;			//
	
	
	
	
}
