package com.example.bookreview.board.repo;
import com.example.bookreview.board.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comments, Long> {

}
