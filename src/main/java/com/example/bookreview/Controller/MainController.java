package com.example.bookreview.Controller;

import com.example.bookreview.domain.Member;
import com.example.bookreview.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class MainController {

    private final MemberService memberService;

    @Autowired
    public MainController(MemberService memberService){
        this.memberService = memberService;
    }
    @GetMapping("/")
    public String main() throws IOException {
        return "main";
    }
}
