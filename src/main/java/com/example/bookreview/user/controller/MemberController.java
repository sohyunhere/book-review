package com.example.bookreview.user.controller;

import com.example.bookreview.Message;
import com.example.bookreview.user.model.Member;
import com.example.bookreview.user.model.SignupDto;
import com.example.bookreview.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String goMypage(Model model, Authentication auth){
        Member loginuser = (Member) auth.getPrincipal();
        model.addAttribute("loginUser", loginuser);

        return "member/mypage";
    }

    //닉네임 수정하기
    @PostMapping("/member/mypage/nickname")
    public String changeNickname(@RequestParam("nickname") String nickname,  Authentication auth){
        Member loginuser = (Member) auth.getPrincipal();
        memberService.updateNickname(loginuser.getMemberId(), nickname);
        return "redirect:/";
    }

    //비밀번호 수정 폼으로 이동
    @GetMapping("/member/mypage/password")
    public String goChangePassword(){
        return "member/passwordForm";
    }

    //비밀번호 수정하기
    @PostMapping("/member/mypage/password")
    public ModelAndView changePassword(@RequestParam("originPassword") String originPW, @RequestParam("newPassword") String newPW, Authentication auth){
        ModelAndView mav = new ModelAndView();

        if(memberService.changePassword((Member) auth.getPrincipal(), originPW, newPW) == -1L){
            mav.addObject("data", new Message("현재 비밀번호를 잘못 입력하셨습니다.", "/member/mypage/password"));
            mav.setViewName("message");
        }
        else{
            mav.addObject("data", new Message("비밀번호 수정이 완료되었습니다", "/member/mypage"));
            mav.setViewName("message");
        }
        return mav;
    }

}
