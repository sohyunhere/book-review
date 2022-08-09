package com.example.bookreview.board.repo;

import com.example.bookreview.board.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepo extends JpaRepository<Post, Long> {
    List<Post> findByCategoryCategoryId(Long categoryId);
}
