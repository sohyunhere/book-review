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
public class CommentsDto {
    Long commentId;
    Long postId;
    Long memberId;
    String content;
    Date writtenDate;

    public CommentsDto(Long commentId, Long postId, Long memberId, String content, Date writtenDate) {
        this.commentId = commentId;
        this.postId = postId;
        this.memberId = memberId;
        this.content = content;
        this.writtenDate = writtenDate;
    }
}
