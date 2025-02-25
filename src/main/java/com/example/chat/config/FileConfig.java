package com.example.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * /files/** 요청을 local "uploads" 폴더로 매핑
 */
@Configuration
@EnableWebMvc
public class FileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 외부 디렉토리 "uploads" → /files/ URL에 매핑
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("file:uploads/");
    }
}
