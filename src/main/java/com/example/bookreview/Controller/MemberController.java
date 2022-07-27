package com.example.bookreview.Controller;

import com.example.bookreview.Message;
import com.example.bookreview.dto.SignupDto;
import com.example.bookreview.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //회원가입 폼으로 이동
    @GetMapping("/member/register")
    public String goSignup(){
        return "member/signupForm";
    }

    //회원가입
    @PostMapping("/member/register")
    public ModelAndView register(HttpServletRequest request,
                                 @Valid SignupDto form, ModelAndView mav){
       if(memberService.join(form)){//회원가입 성공
           mav.addObject("data", new Message("회원가입이 완료되었습니다", "/"));
           mav.setViewName("message");

           return mav;
       }else{//회원가입 실패

           mav.addObject("data", new Message("중복된 이메일 혹은 비밀번호가 서로 다릅니다.", "/member/register"));
           mav.setViewName("message");
           return mav;
       }
    }

    //로그인 폼으로 이동
    @GetMapping("/member/login")
    public String goSignin(){
        return "member/signinForm";
    }

}
