package com.example.bookreview.board.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@RestController
public class ImageController implements WebMvcConfigurer {
    @Value("${com.example.upload.path}") // application.properties의 변수
    private String uploadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        uploadPath += File.separator + "editorUpload";
        registry
                // 이미지 파일의 요청 경로를 지정한다.
                .addResourceHandler("/editorUpload/**")
                // 이미지 파일을 불러올 로컬 저장소의 위치를 지정한다.
                .addResourceLocations(uploadPath);
    }
}