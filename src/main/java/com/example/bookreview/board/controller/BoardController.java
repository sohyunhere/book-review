package com.example.bookreview.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    //글쓰기 페이지로 이동
    @GetMapping("/board/gowrite")
    public String goWrite() {
        return "board/wr_post";
    }

}
