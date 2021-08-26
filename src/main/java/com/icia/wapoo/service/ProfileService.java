package com.icia.wapoo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.wapoo.dao.ProfileDao;
import com.icia.wapoo.model.Profile;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileDao profileDao;
	
	//프로필 조회 profileDao에서 가져옴
	public Profile profileSelect(int memberId)
	{
		Profile profile = profileDao.checkProfile(memberId);
		return profile;
	}
	
	/*
	//프로필 조회
	public Profile profileSelect(int memberId) 
	{
		List<Profile> profile = profileDao.profileSelect(memberId);
		
		
		System.out.println("프로필 : " + profile);
		
		
		return new Profile();
	}
	 */
}
