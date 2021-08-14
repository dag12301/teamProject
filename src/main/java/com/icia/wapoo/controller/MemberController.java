package com.icia.wapoo.controller;

import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Member;
import com.icia.wapoo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
public class MemberController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private MemberService memberService;

    // 로그인 ( http://localhost:8083/api/member/login )
    @PostMapping("/member/login")
    public ResponseEntity login(
            @RequestBody Map<String, Object> loginData,
            HttpServletResponse response) {

        try {
            // 로그인시도 -- 로직을 서비스로 옮겨야하나
            Member member = memberService.getMember(
                    (String) loginData.get("loginId"),
                    (String) loginData.get("loginPwd")
            );
            // id, name, email 으로 JWT 토큰 생성 진행
            String token = jwtService.create(member);

            System.out.println("[생성된토큰] "+token);

            response.setHeader("Authorization", token);
            response.setStatus(200);


            return new ResponseEntity("성공적으로 발행", HttpStatus.OK);

        } catch (Exception e){
            log.error("로그인 실패");
            return new ResponseEntity("토큰 생성중에 오류가 발생", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    	
    	
    }

    @PostMapping("/member/join")
    public ResponseEntity join(
            @RequestBody Map<String, Object> memberData,
            HttpServletResponse res) {
        int result = memberService.joinMember(memberData);
        if( result > 0) {
           return new ResponseEntity("데이터가 성공적으로 삽입됨", HttpStatus.OK);
        } else {
            return new ResponseEntity("내부 서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
