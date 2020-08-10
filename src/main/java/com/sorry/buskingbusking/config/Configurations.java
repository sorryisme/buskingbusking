package com.sorry.buskingbusking.config;

import com.sorry.buskingbusking.interceptor.AuthIntercepetor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Configurations implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthIntercepetor())
                .addPathPatterns("/*")
                .excludePathPatterns("/auth/*")
                .excludePathPatterns("/index")
                .excludePathPatterns("/");
    }
}
