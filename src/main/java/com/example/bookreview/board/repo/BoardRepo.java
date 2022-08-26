package com.example.bookreview.board.repo;

import com.example.bookreview.board.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepo extends JpaRepository<Post, Long> {
    List<Post> findByCategoryCategoryId(Long categoryId);
    List<Post> findByMemberMemberIdOrderByPostIdDesc(Long memberId);

    //글제목으로 검색
    @Query("Select p from Post p where p.postTitle like %?1% order by p.viewCount desc")
    List<Post> searchByPostTitleOrderByViewCount(String postTitle);
    //글내용으로 검색
    @Query("Select p from Post p where p.content like %?1% order by p.viewCount desc")
    List<Post> searchByContentOrderByViewCount(String content);
    //책 제목으로 검색
    @Query("Select p from Post p where p.bookTitle like %?1% order by p.viewCount desc")
    List<Post> searchByBookTitleOrderByViewCount(String title);
    //저자로 검색
    @Query("Select p from Post p where p.author like %?1% order by p.viewCount desc")
    List<Post> searchByAuthorOrderByViewCount(String author);
}
