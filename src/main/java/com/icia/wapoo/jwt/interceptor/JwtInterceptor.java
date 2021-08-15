package com.icia.wapoo.jwt.interceptor;

import com.icia.wapoo.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(HEADER_AUTH);
        System.out.println(token);
        if(token != null && jwtService.validateToken(token)){
            System.out.println("인터셉터 통과");
            return true;
        }else{
            throw new RuntimeException("인터셉터에서 걸림");
        }
    }
}

