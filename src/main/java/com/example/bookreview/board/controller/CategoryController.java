package com.example.bookreview.board.controller;

import com.example.bookreview.board.model.Category;
import com.example.bookreview.board.model.Post;
import com.example.bookreview.board.service.BoardService;
import com.example.bookreview.board.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BoardService boardService;

    //카테고리별 페이지로 이동
    @GetMapping("/category/{categoryId}")
    public String postListByCategory(@PathVariable("categoryId") Long id, Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("categoryId", id);

        return "board/c_postList";
    }
    //카테고리 게시글
    @ResponseBody
    @PostMapping("/category/post/{categoryId}")
    public ResponseEntity<Map<String, Object>> categoryPost(@PathVariable("categoryId") Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("posts", boardService.findListByCategoryId(id));
        return ResponseEntity.ok(map);
    }
}
