package com.example.bookreview.board.service;

import com.example.bookreview.board.model.Category;
import com.example.bookreview.board.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public Category findCategoryById(Long id){
        Optional<Category> result = Optional.ofNullable(categoryRepo.findByCategoryId(id).orElseThrow(() -> new IllegalStateException("카테고리가 존재하지 않습니다.")));
        return result.get();
    }

    public List<Category> findAll(){
        return categoryRepo.findAll();
    }
}
