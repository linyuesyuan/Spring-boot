package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("static/**").addResourceLocations("classpath:/static/");
        //不懂
//        registry.addResourceHandler("/UPLOAD/**").addResourceLocations("file:F:/UPLOAD/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home/login").setViewName("user/login");
        registry.addViewController("/admin/login").setViewName("admin/login");
        registry.addViewController("/admin/rbac").setViewName("admin/rbac");
    }
}
