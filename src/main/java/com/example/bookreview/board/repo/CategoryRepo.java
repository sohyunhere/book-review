package com.example.bookreview.board.repo;

import com.example.bookreview.board.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryId(Long categoryId);
}
