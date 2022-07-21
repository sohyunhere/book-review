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
    public ModelAndView main(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");

        HttpSession session = request.getSession();
        Member user = (Member) session.getAttribute("loginUser");
        if(user != null){//로그인되어있는 사용자
            System.out.println("MainController - 로그인성공");
        }
        else{//로그인 전 사용자
            System.out.println("MainController - 로그인 기록 없음");
        }
        return mav;
    }
//    @GetMapping("/")
//    public String home(){
//        return "main";
//    }
}
