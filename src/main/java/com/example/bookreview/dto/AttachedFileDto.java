package com.example.bookreview.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AttachedFileDto {
    Long fileId;
    Long postId;
    int fSize;
    String fType;
    String name;
    Date writtenDate;

    public AttachedFileDto(Long fileId, Long postId, int fSize, String fType, String name, Date writtenDate) {
        this.fileId = fileId;
        this.postId = postId;
        this.fSize = fSize;
        this.fType = fType;
        this.name = name;
        this.writtenDate = writtenDate;
    }
}
