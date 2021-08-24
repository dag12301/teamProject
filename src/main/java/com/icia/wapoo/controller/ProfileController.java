package com.icia.wapoo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Profile;
import com.icia.wapoo.service.MemberService;
import com.icia.wapoo.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

	@Autowired
    private ProfileService profileService;
	
	//JWT 값 받기
	@Autowired
    private JwtService jwtService;
    @Autowired
    private MemberService memberService;
	
    private int getMemberIdByRequest(HttpServletRequest request) {
        System.out.println("받은 토큰으로 멤버를 검색합니다");
        String token = jwtService.resolveToken(request);
        Map<String, Object> claims = jwtService.getUserInfo(token);
        return ((Integer) claims.get("memberId")).intValue();
    }
    
	//게시글 조회
    /*
    @PostMapping("/myProfile")
	public List<Profile> profileSelect(HttpServletRequest request)
	{
		String token = jwtService.resolveToken(request);  
		System.out.println("토큰 : " + token);
		Map<String, Object> claims = jwtService.getUserInfo(token);
		
		System.out.println("토큰정보? : " + claims);
		List<Profile> profileList = profileService.profileSelect(token);
	      
        return profileList;
	}
    */
    
	@PostMapping("/myProfile")
	public ResponseEntity list(HttpServletRequest request)
	{
		String token = jwtService.resolveToken(request);  
		System.out.println("토큰 : " + token);
		
		Profile profile = profileService.profileSelect(token);
		System.out.println("토큰? : " + profile);
	      
		return new ResponseEntity(profile, HttpStatus.OK);
	}
	
	/*
	 * 
	@PostMapping("/myProfile")
	public ResponseEntity myProfile(HttpServletRequest request)
	{
		Integer memberId = null;
        memberId = getMemberIdByRequest(request);
		Profile profile = null;
		profile = profileService.profileSelect(memberId);
	      
		return new ResponseEntity(profile, HttpStatus.OK);
	}
	 */
	
	
	
}