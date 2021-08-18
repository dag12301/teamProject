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
     * �뿬湲곗뿉 �벑濡앸맂 �쐞移섎뒗 �넗�겙�뾾�씠 吏꾪뻾 媛��뒫�븿.
     */
    private static final String[] EXCLUDE_PATHS = {
            "/api/member/register",
            "/api/member/login",
            "/test",
            "/**", // 媛쒕컻�슜�씠�씪 �쟾遺� �뿴�뼱�몺
            "/api/validation/**",
            "/article/**",
            "/article/board",
            "serviceCenter/qna"
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
