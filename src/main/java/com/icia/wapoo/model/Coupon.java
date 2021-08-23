package com.icia.wapoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
	private int couponId;
	private String couponNumber;
	private String couponName;
	private String couponEnd;
	private String status;
	private int couponPrice;
	private int discountRate;
	private String publishedDate;
	private int food_id;
	private int event_id;
}
