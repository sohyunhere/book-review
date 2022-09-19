package com.example.bookreview.board.controller;

import com.example.bookreview.board.model.CommentsDto;
import com.example.bookreview.board.service.CommentService;
import com.example.bookreview.user.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
        Long commentId = commentService.registerComment(dto, member);

        return Math.toIntExact(commentId);
    }

    //댓글 삭제
    @GetMapping("/board/delete/comment/{commentId}")
    public int deleteComment(@PathVariable("commentId") Long id){
        Long commentId = commentService.deleteComment(id);
        return Math.toIntExact(commentId);
    }

    //댓글 수정
    @PostMapping("/board/update/comment/{commentId}")
    public int updateComment(@PathVariable("commentId") Long id,
                             @RequestParam(value="content") String content){
        Long commentId = commentService.updateComment(id, content);
        return Math.toIntExact(commentId);
    }
}
