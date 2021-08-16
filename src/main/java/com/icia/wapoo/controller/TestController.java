package com.icia.wapoo.controller;

import com.icia.wapoo.dao.LoginInfoDao;
import com.icia.wapoo.dao.MemberDao;
import com.icia.wapoo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    public MemberService memberService;

    public LoginInfoDao loginInfoDao;

    public MemberDao memberDao;


    @Autowired
    public TestController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @GetMapping(value="/test")
    public String testA(@RequestParam Map<String, Object> userData)
    {
        System.out.println();
    	return "테스트요청";
    }

    
    
}
