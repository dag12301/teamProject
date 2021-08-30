package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCoupon {
	 private int id;			//테이블 번호
	 private int couponId;		//쿠폰 아이디
	 private int couponHolder;	//유저 아이디
	 private char couponUsed;	//사용여부
	 private String usedDate;	//사용날짜
	 private int totalDiscountPrice; //할인금액
	 
	 private String couponName;		//쿠폰이름
	 private String couponEnd;		//쿠폰 종료일
	 
	 
}
