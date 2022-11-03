package com.example.bookreview.board.service;

import com.example.bookreview.board.model.Post;
import com.example.bookreview.board.model.PostDto;
import com.example.bookreview.user.model.Member;
import com.example.bookreview.user.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;
    @Test
    @Transactional
    void registerPost() {
        Member user = memberService.findMemberByEmail("shsh@gmail.com");

        PostDto dto = new PostDto();
        dto.setCategoryId(1L);
        dto.setPostTitle("test post");
        dto.setReadDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        dto.setBookTitle("test book");
        dto.setContent("content");
        dto.setWrittenDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        dto.setAuthor("test author");
        dto.setPublisher("test publisher");
        dto.setLat(37.528867);
        dto.setLng(126.957234);
        Long postId = boardService.registerPost(dto,user);
        System.out.println("postId는 "+ postId);

        Post post = boardService.findPostBypostId(postId);
        assertThat(post.getPostTitle()).isEqualTo(dto.getPostTitle());
    }

    @Test
    void updateVisit() {
        Long postId = 1L;
        Post post = boardService.findPostBypostId(postId);

        boardService.updateVisit(post.getPostId(), post.getViewCount());

        Post post1 = boardService.findPostBypostId(postId);
        System.out.println("변경전: "+ post.getViewCount());
        System.out.println("변경후: " + post1.getViewCount());

        assertFalse(post.getViewCount().equals(post1.getViewCount()));
    }

}