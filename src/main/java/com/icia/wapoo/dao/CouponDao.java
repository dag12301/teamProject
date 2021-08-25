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
	
    int insertCoupon(Coupon coupon);

    List<Coupon> selectCouponList(@Param("startLimit") int startLimit, @Param("endLimit") int endLimit, @Param("option") String option);

    int selectCouponListCount(@Param("option") String option);
}