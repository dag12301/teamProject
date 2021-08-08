package com.icia.wapoo.controller;

import com.icia.wapoo.jwt.service.JwtService;
import com.icia.wapoo.model.Member;
import com.icia.wapoo.model.Result;
import com.icia.wapoo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Result<Map<String, Object>> login(
            @RequestBody Map<String, Object> loginData,
            HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        Result result = new Result();
        try {
            // 로그인시도 -- 로직을 서비스로 옮겨야하나
            Member member = memberService.getMember(
                    (String) loginData.get("loginId"),
                    (String) loginData.get("loginPwd")
            );
            // id, name, email 으로 JWT 토큰 생성 진행
            String token = jwtService.create(member);
            response.setHeader("Authorization", token);

            System.out.println("[생성된토큰] "+token);

            resultMap.put("token", token);

            result.setCode(0);
            result.setMsg("토큰 생성 성공");
            result.setStatus(HttpStatus.ACCEPTED);

        } catch (Exception e){
            log.error("로그인 실패");
            resultMap.put("error", e);

            result.setCode(500);
            result.setMsg("내부 서버 오류");
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {;
            result.setData(resultMap);
            return result;
        }
    }

    @PostMapping("/member/join")
    public Result<Map<String, Object>> join(
            @RequestBody Map<String, Object> memberData,
            HttpServletResponse res) {
        Result result = memberService.joinMember(memberData);
        return result;
    }
}
