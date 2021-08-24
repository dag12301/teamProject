package com.icia.wapoo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.icia.wapoo.model.Coupon;

@Repository
@Mapper
public interface CouponDao {
	
    public int insertCoupon(Coupon coupon);

    List<Map<String, Object>> selectCouponList(@Param("startLimit") int startLimit, @Param("endLimit") int endLimit, @Param("option") String option);

    int selectCouponListCount(@Param("option") String option);
    
    int updateCouponStatus(@Param("couponId") int couponId, @Param("status") String status);
}