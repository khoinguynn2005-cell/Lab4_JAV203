package com.example.lab4.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadDir = new File("uploads").getAbsoluteFile().toURI().toString(); // vd: file:/D:/.../Lab4/uploads/
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadDir, "classpath:/static/images/");
    }
}
