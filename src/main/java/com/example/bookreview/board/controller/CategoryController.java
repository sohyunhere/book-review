package com.example.bookreview.board.controller;

import com.example.bookreview.board.model.Category;
import com.example.bookreview.board.model.Post;
import com.example.bookreview.board.service.BoardService;
import com.example.bookreview.board.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BoardService boardService;

    //카테고리별 게시글 리스트 출력
    @GetMapping("/category/{categoryId}")
    public String postListByCategory(@PathVariable("categoryId") Long id, Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        List<Post> posts = boardService.findListByCategoryId(id);
        model.addAttribute("posts", posts);
        return "board/c_postList";
    }
}
