package com.icia.wapoo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.icia.wapoo.model.Profile;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProfileDao {

	//프로필 보기
	public Profile profileSelect(String profileId);
	
	//List<Profile> profileSelect(@Param("memberId") int memberId);

	
}
