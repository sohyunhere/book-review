package com.example.bookreview.board.model;

import com.example.bookreview.user.model.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class PostDto {
//    private Long userId;
    private Member member;
    private Category category;
    private Long categoryId;
    private String postTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date readDate;
    private String bookTitle;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date writtenDate;
    private Long viewCount;


    private String author;
    private String publisher;

    //빼기
    private String formFile;
    private Long locationId;
    public Post toEntity(){
        return Post.builder()
                .member(member)
//                .memberId(userId)
                .postTitle(postTitle)
                .readBookDate(new java.sql.Date(readDate.getTime()))
                .bookTitle(bookTitle)
                .content(content)
                .viewCount(viewCount)
                .author(author)
                .publisher(publisher)
//                .categoryId(categoryId)
                .category(category)
                .writtenDate(writtenDate)
                .build();
    }


}
