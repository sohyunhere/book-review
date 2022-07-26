package com.example.bookreview;

import com.example.bookreview.board.model.Category;
import com.example.bookreview.board.service.BoardService;
import com.example.bookreview.board.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;
    private final BoardService boardService;

    @GetMapping("/")
    public String main(Model model) throws IOException {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "main";
    }

    //메인 게시글
    @ResponseBody
    @GetMapping("/main/posts")
    public ResponseEntity<Map<String, Object>> posts() {
        Map<String, Object> map = new HashMap<>();
        map.put("posts", boardService.findAllByLatest());

        return ResponseEntity.ok(map);
    }
    //최신순
    @ResponseBody
    @GetMapping("/latest")
    public ResponseEntity<Map<String, Object>> latest() {
        Map<String, Object> map = new HashMap<>();
        map.put("posts", boardService.findAllByLatest());

        return ResponseEntity.ok(map);
    }
    //조회순
    @ResponseBody
    @GetMapping("/popular")
    public ResponseEntity<Map<String, Object>> popular() {
        Map<String, Object> map = new HashMap<>();
        map.put("posts", boardService.findAllByView());

        return ResponseEntity.ok(map);
    }
}
