package com.example.bookreview.board.controller;

import com.example.bookreview.Message;
import com.example.bookreview.board.model.Category;
import com.example.bookreview.board.model.Post;
import com.example.bookreview.board.model.PostDto;
import com.example.bookreview.board.service.BoardService;
import com.example.bookreview.board.service.CategoryService;
import com.example.bookreview.user.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final CategoryService categoryService;
    private final BoardService boardService;

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }

    //글쓰기 페이지로 이동
    @GetMapping("/board/write")
    public String goWrite(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "board/wr_post";
    }

    //글 작성
    @PostMapping("/board/write")
    public ModelAndView write(@Valid PostDto dto, Authentication auth) {
        Long userId = ((Member)auth.getPrincipal()).getMemberId();
        Long postId = boardService.registerPost(dto, userId);

        ModelAndView mav = new ModelAndView();

        if(postId != 1L){
            //성공
            mav.addObject("data", new Message("게시글 등록이 완료되었습니다", "/"));
            mav.setViewName("message");
        }
        else{
            mav.addObject("data", new Message("게시글 등록이 실패하였습니다", "/board/write"));
            mav.setViewName("message");
        }

        return mav;
    }

    //글 보기
    @GetMapping("/board/view/{postId}")
    public String postView(@PathVariable("postId") Long id, Model model){
        Post post = boardService.findPostBypostId(id);
        model.addAttribute("post", post);

        return "board/v_post";
    }
    //글 수정
    //글 삭제
}
