package com.example.bookreview.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UploadController {

//    @Value("${com.example.upload.path}") // application.properties의 변수
//    private String uploadPath;
//
//    //위지윅 에디터 이미지 업로드
//    @PostMapping("/board/image/editorUpload")
//    public void uploadImage(@RequestParam("image") MultipartFile upload) throws IOException {
//        log.info("post toast ui img upload");
//
////        //랜덤 문자 생성
//        UUID uid = UUID.randomUUID();
//        OutputStream out = null;
//        PrintWriter printWriter = null;
//
//        try{
//            String fileName = upload.getOriginalFilename();//파일 이름 가져오기
//            byte[] bytes = upload.getBytes();
//
//            String toastUploadPath = uploadPath + File.separator + "editorUpload" + File.separator + uid + "_" + fileName;
//        }catch(Exception e){
//            throw e;
//        }
//
//    }
}
