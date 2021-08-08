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

    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        } else {
            final String token = request.getHeader(HEADER_AUTH);
            if (token != null && token.length() > 0){
                jwtService.checkValid(token);
                log.trace("token : {}", token);
                return true;
            } else {
                throw new RuntimeException("token이 없습니다"); // 커스텀 오류 만들어야겠음*
            }
        }
    }
}
