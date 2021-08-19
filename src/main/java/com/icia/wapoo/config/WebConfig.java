package com.icia.wapoo.config;

import com.icia.wapoo.jwt.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebMvc
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
            "/**",
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

    /**
     * CORS 해제
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("Authorization");
    }
}
