package com.example.bookreview.board.service;

import com.example.bookreview.board.model.Category;
import com.example.bookreview.board.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;
    @Transactional
    public Category findCategoryById(Long id){
        Optional<Category> result =  categoryRepo.findByCategoryId(id);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    public List<Category> findAll(){
        return categoryRepo.findAll();
    }
}
