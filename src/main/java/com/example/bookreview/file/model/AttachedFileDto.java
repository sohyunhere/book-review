package com.example.bookreview.file.model;

import lombok.*;

import java.util.Date;

@Data
public class AttachedFileDto {
    private Long fileId;//파일아이디
    private Long postId;//게시글아이디
    private int fSize;//사이즈
    private String fType;//타입
    private String name;//이름?
    private Date writtenDate;//등록일

    private String files[]; //파일명
}
