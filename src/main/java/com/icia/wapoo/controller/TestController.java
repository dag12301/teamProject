package com.icia.wapoo.controller;

import com.icia.wapoo.model.Member;
import com.icia.wapoo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {

    public MemberService memberService;

    @Autowired
    public TestController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @PostMapping(value="api/test/join")
    public String test(HttpServletRequest request,HttpServletResponse response)
    {
    	boolean log = false;
    	
    	String id = request.getParameter("id");
    	String pwd = request.getParameter("pwd");
    	
    	System.out.println();
    	System.out.println(id);
    	System.out.println(pwd);
    	System.out.println();
    	
    	if("test" == id && "1234" == pwd)
    	{
    		log = !log;
    	}
    	
    	return "/about";
    }

   
    @GetMapping("api/member/test")
    public String testing(@RequestBody Map<String, Object> loginData,
            HttpServletResponse response)
    {
    	System.out.println("2222222222222222");
    	
    	return "/Home";
    }
    
    
    
    
    
}
