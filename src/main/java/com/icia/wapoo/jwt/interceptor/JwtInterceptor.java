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

    /**
     * 작성자 노철희
     * 토큰 확인을 위한 인터셉터 핸들러
     * @param request header에 달린 토큰정보를 가져온다
     * @param response 라이프사이클을 진행하기 위해서 필요하다
     * @param handler 인터셉터의 prehandle 메서드를 정의하기위해 필요한 인자.
     * @return 토큰의 유효기간, 로직을 분석하고 유효하면 다음으로 진행한다.
     * @throws Exception 이상한 요청은 전부 걸린다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(HEADER_AUTH);
        System.out.println("인터셉터에서 받은 토큰 -> "+ token);
        if(token != null && jwtService.validateToken(token)){
            System.out.println("인터셉터 통과");
            return true;
        }else{
            throw new RuntimeException("==인터셉터에서 걸렸습니다== WebConfig 참조해주세요..");
        }
    }
}

