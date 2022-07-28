package com.example.bookreview.board.model;

import lombok.*;

import java.util.Date;

@Data
public class CommentsDto {
    Long commentId;
    Long postId;
    Long memberId;
    String content;
    Date writtenDate;

}
