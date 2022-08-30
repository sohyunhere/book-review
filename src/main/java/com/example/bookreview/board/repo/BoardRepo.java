package com.example.bookreview.board.repo;

import com.example.bookreview.board.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepo extends JpaRepository<Post, Long> {
    List<Post> findByCategoryCategoryId(Long categoryId);
    List<Post> findByMemberMemberIdOrderByPostIdDesc(Long memberId);

    //글제목으로 검색
    List<Post> findByPostTitleContainingOrderByViewCount(String postTitle);
    //글내용으로 검색
    List<Post> findByContentContainingOrderByViewCount(String content);
    //책 제목으로 검색
    List<Post> findByBookTitleContainingOrderByViewCount(String title);
    //저자로 검색
    List<Post> findByAuthorContainingOrderByViewCount(String author);
}
