package com.example.demo.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path postUploadDir = Paths.get("./image-posts/");
        String postUploadPath = postUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/image-posts/**").addResourceLocations("file:/" + postUploadPath + "/");
    }

}
