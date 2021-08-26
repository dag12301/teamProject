package com.icia.wapoo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.icia.wapoo.model.Profile;
import com.icia.wapoo.model.Store;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProfileDao {

	//profileMapper랑 연결됨 
	//@Param = #{memberId}
	//Profile = resultType
	//parameterType = 여러개 가져와야할때 지정
	Profile checkProfile(@Param("memberId") int memberId);
	

	
}
