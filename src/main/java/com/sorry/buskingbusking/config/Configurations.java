package com.sorry.buskingbusking.config;

import com.sorry.buskingbusking.interceptor.AuthIntercepetor;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Configurations implements WebMvcConfigurer {

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthIntercepetor())
                .addPathPatterns("/*")
                .excludePathPatterns("/auth/*")
                .excludePathPatterns("/index")
                .excludePathPatterns("/");
    }*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(60 * 60);

        registry.addResourceHandler("/image/**")
                .addResourceLocations("classpath:/static/image/")
                .setCachePeriod(60 * 60);

        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/")
                .setCachePeriod(60 * 60);
    }

}
