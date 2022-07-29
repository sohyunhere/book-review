package com.example.bookreview.user.controller;

import com.example.bookreview.Message;
import com.example.bookreview.user.auth.PrincipalDetails;
import com.example.bookreview.user.model.SignupDto;
import com.example.bookreview.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    //회원가입 폼으로 이동
    @GetMapping("/member/register")
    public String goSignup(){
        return "member/signupForm";
    }

    //회원가입
    @PostMapping("/member/register")
    public ModelAndView register(@Valid SignupDto form, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView();

        System.out.println("error: "+ bindingResult.hasErrors());
        if(bindingResult.hasErrors()){
            mav.setViewName("/member/signupForm");
            return mav;
        }
       if(memberService.join(form)){//회원가입 성공
           mav.addObject("data", new Message("회원가입이 완료되었습니다", "/member/login"));
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

    //마이페이지로 이동
    @GetMapping("/member/mypage")
    public String goMypage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        model.addAttribute("loginUser", principalDetails.getMember());
        return "member/mypage";
    }

}
