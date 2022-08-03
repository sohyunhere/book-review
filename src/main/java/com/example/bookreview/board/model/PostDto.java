package com.example.bookreview.board.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class PostDto {
    private Long userId;
    private Long categoryId;
    private String postTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date readDate;
    private String bookTitle;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date writtenDate;
    private int viewCount;


    private String author;
    private String publisher;

    //빼기
    private String formFile;
    private Long locationId;
    public Post toEntity(){
        return Post.builder()
                .memberId(userId)
                .postTitle(postTitle)
                .readBookDate(new java.sql.Date(readDate.getTime()))
                .bookTitle(bookTitle)
                .content(content)
                .viewCount(viewCount)
                .author(author)
                .publisher(publisher)
                .categoryId(categoryId)
                .writtenDate(writtenDate)
                .build();
    }

}
