package com.example.bookreview.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class BoardController {

    @GetMapping("/hello")
    public String Hello(Model model) {
        model.addAttribute("data", "helloooo!");
        return "hello";
    }
}