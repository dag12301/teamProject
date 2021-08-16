package com.icia.wapoo.config;

import com.icia.wapoo.jwt.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;
    /**
     * 여기에 등록된 위치는 토큰없이 진행 가능함.
     */
    private static final String[] EXCLUDE_PATHS = {
            "/api/member/register",
            "/api/member/login",
            "/test",
            "/**", // 개발용이라 전부 열어둠
            "/api/validation/**",
            "/article/**",
            "/article/board"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("제거된 경로: ");
        Arrays.stream(EXCLUDE_PATHS).forEach(System.out::println);
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("Authorization");
    }
}
