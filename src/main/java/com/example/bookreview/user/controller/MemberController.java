package com.example.bookreview.user.controller;

import com.example.bookreview.Message;
import com.example.bookreview.user.model.Member;
import com.example.bookreview.user.model.SignupDto;
import com.example.bookreview.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    //회원가입 폼으로 이동
    @GetMapping("/member/register")
    public String goSignup(){
        return "member/signupForm";
    }

    //회원가입
    @PostMapping("/member/register")
    @ResponseBody
    public int register(@RequestBody SignupDto dto) {

        try {
            memberService.join(dto);
        } catch (Exception e) {
            return 0;
        }
        return 1;
//        memberService.join(dto);

//        System.out.println("error: " + bindingResult.hasErrors());
//        if (bindingResult.hasErrors()) {
//            mav.setViewName("/member/signupForm");
//            return mav;
////        }
//        if (memberService.join(dto)) {//회원가입 성공
//            mav.addObject("data", new Message("회원가입이 완료되었습니다", "/member/login"));
//            mav.setViewName("message");
//
//            return mav;
//        } else {//회원가입 실패
//            mav.addObject("data", new Message("중복된 이메일 혹은 비밀번호가 서로 다릅니다.", "/member/register"));
//            mav.setViewName("message");


//        }
    }

    //이메일이 중복하는지
    @PostMapping("/member/emailCheck")
    @ResponseBody
    public int emailCheck(@RequestParam("email") String email) throws Exception{
        try{
            memberService.findMemberByEmail(email);
        }catch(IllegalStateException e){
            return 0;//중복아님
        }
        return 1; //중북
    }

    //로그인 폼으로 이동
    @GetMapping("/member/login")
    public String goSignin(){
        return "member/signinForm";
    }
//
//    //마이페이지로 이동
//    @GetMapping("/member/mypage")
//    public String goMypage(Model model, Authentication auth){
//        Member loginuser = (Member) auth.getPrincipal();
//        model.addAttribute("loginUser", loginuser);
//
//        return "member/mypage";
//    }

    //닉네임 수정하기
//    @PostMapping("/member/mypage/nickname")
//    public ModelAndView changeNickname(@RequestParam("nickname") String nickname,  Authentication auth, ModelAndView mav){
//        Member user = (Member) auth.getPrincipal();
//        Member changedUser = memberService.updateNickname(user.getMemberId(), nickname);
//
//        //세션 수정
//        memberService.changeSession(changedUser);
//
//        mav.addObject("data", new Message("닉네임 수정이 완료되었습니다", "/member/mypage"));
//        mav.setViewName("message");
//        return mav;
//    }

    //비밀번호 수정 폼으로 이동
//    @GetMapping("/member/mypage/password")
//    public String goChangePassword(){
//        return "member/passwordForm";
//    }

    //비밀번호 수정하기
//    @ExceptionHandler(Exception.class)
//    @PostMapping("/member/mypage/password")
//    public ModelAndView changePassword(@RequestParam("originPassword") String originPW, @RequestParam("newPassword") String newPW, Authentication auth) {
////        ModelAndView mav = new ModelAndView();
//
//        memberService.changePassword((Member) auth.getPrincipal(), originPW, newPW);

//        if({
//            mav.addObject("data", new Message("현재 비밀번호를 잘못 입력하셨습니다.", "/member/mypage/password"));
//            mav.setViewName("message");
//        }
//        else{
//            mav.addObject("data", new Message("비밀번호 수정이 완료되었습니다", "/member/mypage"));
//            mav.setViewName("message");
//        }
//        return mav;
//    }

}
