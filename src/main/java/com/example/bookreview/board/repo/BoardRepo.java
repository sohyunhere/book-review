package com.example.bookreview.board.repo;

import com.example.bookreview.board.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<Post, Long> {
}
