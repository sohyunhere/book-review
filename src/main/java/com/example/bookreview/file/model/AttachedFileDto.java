package com.example.bookreview.file.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
public class AttachedFileDto {
    private Long fileId;
    private Long postId;
    private int fSize;
    private String fType;
    private String name;
    private Date writtenDate;
}
