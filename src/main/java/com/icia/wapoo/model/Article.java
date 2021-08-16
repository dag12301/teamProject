package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article{
	private int articleId;		// 寃뚯떆臾� PK
	private String regDate;			// �궇吏�
	private String updateDate;		// �닔�젙 �궇吏�
	private String title;			// �젣紐�
	private String body;			// �궡�슜
	private int hit;				// 議고쉶�닔
	private String status;			// �긽�깭
	private int parant_id;			// 遺�紐� 寃뚯떆湲�
	private int board_id;			// 寃뚯떆�뙋 醫낅쪟
	private int writer_id;			// �옉�꽦�옄

}