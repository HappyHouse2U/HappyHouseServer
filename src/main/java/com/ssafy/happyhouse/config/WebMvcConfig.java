package com.ssafy.happyhouse.config;

import com.ssafy.happyhouse.interceptor.BoardInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BoardInterceptor())
                .addPathPatterns("/board/*")
                .excludePathPatterns("/board/list.do");
    }
}
