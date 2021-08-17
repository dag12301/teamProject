package com.icia.wapoo.controller;


import com.icia.wapoo.dao.LoginInfoDao;
import com.icia.wapoo.dao.MemberDao;
import com.icia.wapoo.model.LoginInfo;
import com.icia.wapoo.model.Member;

import com.icia.wapoo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {

//    public MemberService memberService;
//
//    public LoginInfoDao loginInfoDao;
//
//    public MemberDao memberDao;
//
//
//    @Autowired
//    public TestController(MemberService memberService) {
//        this.memberService = memberService;
//    }
//    
   
//
//   
//    @GetMapping(value="/test")
//    public ResponseEntity testing(
//    		
//    		@RequestParam("userId") String id,
//    		HttpServletRequest request,
//            HttpServletResponse response)
//    {
//    	
//    	System.out.println(id);
//    	
//        return new ResponseEntity("성공적으로 발행", HttpStatus.OK);
//    }
//
//    @PostMapping("/test2")
//    public ResponseEntity test2(HttpServletRequest request,
//            					HttpServletResponse response)	
//    {
//    	String id = request.getParameter("id");
//    	
//    	if(id != null)
//    	{
//    		System.out.println("11111");
//    	}
//    	if(id == null)
//    	{
//    		System.out.println("2222");
//    	}
//    	
//    	System.out.println(id);
//    	
//    	return new ResponseEntity(id, HttpStatus.OK);
//    }
//    
//    
//    
    
    
}
