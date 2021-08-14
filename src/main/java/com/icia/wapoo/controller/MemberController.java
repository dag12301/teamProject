package com.icia.wapoo.controller;

import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Member;
import com.icia.wapoo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
            System.out.println("로그인 정보 조회");
            System.out.println(loginData);
            Member member = memberService.getMemberByLoginInfo(
                    (String) loginData.get("loginId"),
                    (String) loginData.get("password")
            );
            if(member == null){
                return new ResponseEntity("회원정보를 찾지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            System.out.println(member);

            String token = jwtService.create(member);
            System.out.println("[생성된토큰] "+token);

            response.setHeader("Authorization", token);
            response.setStatus(200);


            return new ResponseEntity(member, HttpStatus.OK);

        } catch (Exception e){
            log.error("토큰 생성중에 오류가 발생했습니다.");
            return new ResponseEntity("토큰 생성중에 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Member 객체 정보 얻어오기.
    @PostMapping("/member/info")
    public ResponseEntity getInfo() {
        return new ResponseEntity("성공?", HttpStatus.OK);
    }
}
