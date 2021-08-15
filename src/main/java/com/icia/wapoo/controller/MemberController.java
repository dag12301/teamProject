package com.icia.wapoo.controller;

import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Member;
import com.icia.wapoo.service.MemberService;
import lombok.RequiredArgsConstructor;
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
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
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
    // 로그인 유지를 위한 Member 객체 정보 얻어오기.
    @PostMapping("/member/getInfo")
    public ResponseEntity getInfo(HttpServletRequest request) {
        // 토큰으로 유저객체 구하기.
        String token = jwtService.resolveToken(request);
        System.out.println("test : 받은토큰 -> " + token);
        if(token == null) {
            return new ResponseEntity("토큰이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        boolean isValid = jwtService.validateToken(token);
        System.out.println("test : 유효한 토큰? -> "+isValid);
        if(isValid == false) {
            // 유효하지 않은 토큰
            return new ResponseEntity("유효하지 않은 토큰", HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> infos = jwtService.getUserInfo(token);
        System.out.println("test : 토큰정보 -> " +infos);
        int id = (int) infos.get("memberId");
        Member member = memberService.getMemberBymemberId(id);


        return new ResponseEntity(member, HttpStatus.OK);
    }
}
