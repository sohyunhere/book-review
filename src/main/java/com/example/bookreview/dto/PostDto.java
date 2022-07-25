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
public class PostDto {
    Long postId;
    Long userId;
    Long categoryId;
    String postTitle;
    Date readBookDate;
    String bookTitle;
    String content;
    Date writtenDate;
    int viewCount;
    Long locationId;

    public PostDto(Long postId, Long userId, Long categoryId, String postTitle,
                   Date readBookDate, String bookTitle, String content, Date writtenDate,
                   int viewCount, Long locationId) {
        this.postId = postId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.postTitle = postTitle;
        this.readBookDate = readBookDate;
        this.bookTitle = bookTitle;
        this.content = content;
        this.writtenDate = writtenDate;
        this.viewCount = viewCount;
        this.locationId = locationId;
    }
}
