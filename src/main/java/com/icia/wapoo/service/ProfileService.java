package com.icia.wapoo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.wapoo.dao.MemberDao;
import com.icia.wapoo.dao.ProfileDao;
import com.icia.wapoo.model.LoginInfo;
import com.icia.wapoo.model.Member;
import com.icia.wapoo.model.MemberCoupon;
import com.icia.wapoo.model.Profile;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private MemberDao memberDao;
	
	//프로필 조회 profileDao에서 가져옴
	public Profile profileSelect(int memberId)
	{
		Profile profile = profileDao.checkProfile(memberId);
		return profile;
	}
	
	//프로필 수정하기
	public int insertProfile(Profile profile)
	{
		int count = 0;
		
		try
		{
			count = profileDao.insertProfile(profile);
		}
		catch(Exception e)
		{
			System.out.println("insertProfile 오류: " + e);
		}
		return count;
	}
	
	//비밀번호 수정하기
	public int insertPassWord(LoginInfo loginInfo)
	{
		int count = 0;
		
		try
		{
			System.out.println("loginInfo:   " + loginInfo);
			count = profileDao.insertPassWord(loginInfo);
		}
		catch(Exception e)
		{
			System.out.println("insertPassWord 오류: " + e);
		}
		return count;
	}
	
	//회원 확인
	public LoginInfo deleteProfileCheck(int member_id,String password)
	{
		LoginInfo loginInfo = null;
		
		try
		{
			loginInfo = profileDao.deleteProfileCheck(member_id, password);
		}
		catch(Exception e)
		{
			System.out.println("deleteProfileCheck 오류: " + e);
		}
		
		return loginInfo;
	}
	
	//탈퇴하기
	public int deleteProfile(int member_id)
	{
		int count = 0;
		
		try
		{
			count = profileDao.deleteProfile(member_id);
		}
		catch(Exception e)
		{
			System.out.println("deleteProfile 오류: " + e);
		}
		
		return count;
	}
	
	//쿠폰정보 가져오기
	public List<MemberCoupon> getCoupon (int memberId)
	{
		List<MemberCoupon>  memberCoupon = null;
		
		try
		{
			memberCoupon = profileDao.getCoupon(memberId);
		}
		catch(Exception e)
		{
			System.out.println("쿠폰정보가 없습니다.");
			System.out.println("getCoupon 오류: " + e);
		}
		return memberCoupon;
	}
	
	
	//멤버 정보 가ㅏ져오기
	public Member getMember(int memberId)
	{
		Member member = null;
		
		try
		{
			member = memberDao.selectMemberById(memberId);
		}
		catch(Exception e)
		{
			System.out.println("쿠폰정보가 없습니다.");
			System.out.println("getCoupon 오류: " + e);
		}
		
		return member;
	}
	
	
	
	
	
	
	
	
}
