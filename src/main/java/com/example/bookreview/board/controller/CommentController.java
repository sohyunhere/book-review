package com.example.bookreview.board.controller;

import com.example.bookreview.board.model.CommentsDto;
import com.example.bookreview.board.service.CommentService;
import com.example.bookreview.user.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentController
{
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/comment/write")
    public int write(@RequestBody CommentsDto dto, Authentication auth){
        Member member = (Member) auth.getPrincipal();
        Long commentId;
        try{
            commentId = commentService.registerComment(dto, member);
        }catch (Exception e){
            throw e;
        }
        return Math.toIntExact(commentId);
    }

    //댓글 삭제
    @PostMapping("/board/delete/comment/{commentId}")
    public int deleteComment(@PathVariable("commentId") Long id){
        Long commentId;
        try {
            commentId = commentService.deleteComment(id);
        }catch (Exception e){
            throw e;
        }
        return Math.toIntExact(commentId);
    }
}
