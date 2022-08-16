package com.example.bookreview;

import com.example.bookreview.board.model.Category;
import com.example.bookreview.board.model.Post;
import com.example.bookreview.board.service.BoardService;
import com.example.bookreview.board.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;
    private final BoardService boardService;

    @GetMapping("/")
    public String main(Model model) throws IOException {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        List<Post> posts = boardService.findAll();
        model.addAttribute("posts", posts);
        return "main";
    }
    //최신순
//    @GetMapping("/latest")
//    public String latest(Model model) throws IOException {
//        List<Category> categories = categoryService.findAll();
//        model.addAttribute("categories", categories);
////        List<Post> posts = boardService.findAll();
////        model.addAttribute("posts", posts);
//        return "main";
//    }
//    //조회순
//    @GetMapping("/popular")
//    public String popular(Model model) throws IOException {
//        List<Category> categories = categoryService.findAll();
//        model.addAttribute("categories", categories);
////        List<Post> posts = boardService.findAll();
////        model.addAttribute("posts", posts);
//        return "main";
//    }
}
