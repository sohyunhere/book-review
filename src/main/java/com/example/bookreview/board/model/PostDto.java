package com.example.bookreview.board.model;

import com.example.bookreview.location.model.Location;
import com.example.bookreview.user.model.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class PostDto {
    private Member member;
    private Category category;
    private Long categoryId;
    private String postTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date readDate;
    private String bookTitle;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date writtenDate;
    private Long viewCount;


    private String author;
    private String publisher;

    //빼기
//    private String formFile;

    private Location location;
    private Long locationId;
    private double lat;
    private double lng;
    public Post toEntity(){
        return Post.builder()
                .member(member)
                .postTitle(postTitle)
                .readBookDate(new java.sql.Date(readDate.getTime()))
                .bookTitle(bookTitle)
                .content(content)
                .viewCount(viewCount)
                .author(author)
                .publisher(publisher)
                .category(category)
                .writtenDate(writtenDate)
                .location(location)
                .build();
    }


}
