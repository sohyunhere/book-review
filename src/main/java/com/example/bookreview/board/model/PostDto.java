package com.example.bookreview.board.model;

import lombok.*;

import java.util.Date;

@Data
public class PostDto {
    private Long userId;
    private Long categoryId;
    private String postTitle;
    private Date readBookDate;
    private String bookTitle;
    private String content;
    private Date writtenDate;
    private int viewCount;
    private Long locationId;
    private String author;
    private String publisher;

    public Post toEntity(){
        return Post.builder()
                .postTitle(postTitle)
                .readBookDate(readBookDate)
                .bookTitle(bookTitle)
                .build();
    }

}
