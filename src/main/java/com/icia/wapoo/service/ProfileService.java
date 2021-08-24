package com.icia.wapoo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.wapoo.dao.ArticleDao;
import com.icia.wapoo.dao.ProfileDao;
import com.icia.wapoo.model.Article;
import com.icia.wapoo.model.ImageFile;
import com.icia.wapoo.model.Profile;
import com.icia.wapoo.model.Store;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileDao profileDao;
	
	//프로필 조회
	/*
	public List<Profile> profileSelect(Profile profile)
	{
		List<Profile> profileList = null;
		
		try 
		{
			profileList = profileDao.profileSelect(profile);
		}
		catch(Exception e)
		{
			System.out.println();
			System.out.println(e);
		}
		
		System.out.println(profileList);
		
		
		return profileList;
	}
	*/
	public Profile profileSelect(String profileId) 
	{
		Profile profile = null;
		
		try 
		{
			profile = profileDao.profileSelect(profileId);
		}
		catch(Exception e)
		{
			System.out.println();
			System.out.println(e);
		}
		
		System.out.println(profile);
		
		
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
