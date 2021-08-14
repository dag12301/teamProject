package com.icia.wapoo.jwt.interceptor;

import com.icia.wapoo.jwt.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    private static final String HEADER_AUTH = "accessToken";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) { // 예외
            return true;
        } else {
            final String token = request.getHeader(HEADER_AUTH);
            System.out.println("받은토큰" + token);

            if (token != null && token.length() > 0){
                jwtService.checkValid(token);
                System.out.println("토큰 인증");
                log.trace("token : {}", token);
                return true;
            } else {
                throw new RuntimeException("인증되지 않은 사용자입니다."); // 커스텀 오류 만들어야겠음*
            }
        }
    }
}
