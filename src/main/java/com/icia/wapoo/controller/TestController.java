package com.icia.wapoo.controller;

<<<<<<< HEAD
import com.icia.wapoo.model.LoginInfo;
import com.icia.wapoo.model.Member;
=======
import com.icia.wapoo.dao.LoginInfoDao;
import com.icia.wapoo.dao.MemberDao;
>>>>>>> e0f0338db4ada06b0aa9991639b474ccaabac6ab
import com.icia.wapoo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======
>>>>>>> e0f0338db4ada06b0aa9991639b474ccaabac6ab
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

   
    @GetMapping(value="/test")
    public ResponseEntity testing(
    		
    		@RequestParam("userId") String id,
    		HttpServletRequest request,
            HttpServletResponse response)
    {
    	
    	System.out.println(id);
    	
        return new ResponseEntity("성공적으로 발행", HttpStatus.OK);
    }

    @PostMapping("/test2")
    public ResponseEntity test2(HttpServletRequest request,
            					HttpServletResponse response)	
    {
    	String id = request.getParameter("id");
    	
    	if(id != null)
    	{
    		System.out.println("11111");
    	}
    	if(id == null)
    	{
    		System.out.println("2222");
    	}
    	
    	System.out.println(id);
    	
    	return new ResponseEntity(id, HttpStatus.OK);
    }
    
    
    
    
    
}
