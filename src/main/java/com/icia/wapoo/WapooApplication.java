package com.icia.wapoo;

import com.icia.wapoo.jwt.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


@SpringBootApplication
public class WapooApplication {

    public static void main(String[] args) {
        SpringApplication.run(WapooApplication.class, args);
    }
}
