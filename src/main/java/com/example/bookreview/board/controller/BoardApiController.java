package com.example.bookreview.board.controller;

import com.example.bookreview.board.model.PostDto;
import com.example.bookreview.board.service.BoardService;
import com.example.bookreview.user.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BoardApiController {
    private final BoardService boardService;

    //글 작성
    @PostMapping("/board/write")
    public int write(@RequestBody PostDto dto, Authentication auth) {
        Member member = (Member) auth.getPrincipal();

        Long postId;
        try {
            postId = boardService.registerPost(dto, member);
        } catch (Exception e) {
            throw e;
        }
        return Math.toIntExact(postId);

    }

    //글 수정
    @PostMapping("/board/update/{postId}")
    public int updatePost(@PathVariable("postId") Long id, @RequestBody PostDto dto){
        try{
            boardService.updatePost(id,dto.getContent());
        } catch (Exception e) {
            throw e;
        }
        return Math.toIntExact(id);
    }


    //글 삭제
    @DeleteMapping("/board/{postId}")
    public int deletePost(@PathVariable("postId") Long id){
        Long postId;
        try {
            postId = boardService.deletePostById(id);
        }catch (Exception e){
            throw e;
        }
        return Math.toIntExact(postId);
    }
}
