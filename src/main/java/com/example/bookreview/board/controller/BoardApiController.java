package com.example.bookreview.board.controller;

import com.example.bookreview.board.model.PostDto;
import com.example.bookreview.board.service.BoardService;
import com.example.bookreview.user.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BoardApiController {
    private final BoardService boardService;

    //글 작성
    @PostMapping("/board/write")
    public int write(@RequestBody PostDto dto, Authentication auth) {
        Member member = (Member) auth.getPrincipal();
        Long postId = boardService.registerPost(dto, member);

        return Math.toIntExact(postId);
    }

    //글 수정
    @PostMapping("/board/update/{postId}")
    public int updatePost(@PathVariable("postId") Long id, @RequestBody PostDto dto){

        boardService.updatePost(id,dto.getContent());

        return Math.toIntExact(id);
    }


    //글 삭제
    @PostMapping("/board/delete/post/{postId}")
    public int deletePost(@PathVariable("postId") Long id){
        Long postId = boardService.deletePostById(id);

        return Math.toIntExact(postId);
    }

    //내가 작성한 게시글 가져오기
    @GetMapping ("/board/mypost/list")
    public ResponseEntity<Map<String, Object>> getMyPost(Authentication auth) {

        Member member = (Member)auth.getPrincipal();

        Map<String, Object> map = new HashMap<>();
        map.put("posts", boardService.findPostListByUser(member.getMemberId()));
        return ResponseEntity.ok(map);
    }

    //글 검색
    @GetMapping("/board/search")
    public  ResponseEntity<Map<String, Object>> search(
            @RequestParam(value="searchType") String type,@RequestParam(value="search") String word){

        Map<String, Object> map = new HashMap<>();
        map.put("posts", boardService.searchList(word, Integer.parseInt(type)));

        return ResponseEntity.ok(map);
    }

}
