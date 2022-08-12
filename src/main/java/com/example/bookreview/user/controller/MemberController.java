package com.example.bookreview.user.controller;

import com.example.bookreview.user.model.Member;
import com.example.bookreview.user.model.SignupDto;
import com.example.bookreview.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    }

    //이메일이 중복하는지
    @PostMapping("/member/emailCheck")
    @ResponseBody
    public int emailCheck(@RequestParam("email") String email){
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

    //마이페이지로 이동
    @GetMapping("/member/mypage")
    public String goMypage(Model model, Authentication auth){
        Member loginuser = (Member) auth.getPrincipal();
        model.addAttribute("loginUser", loginuser);

        return "member/mypage";
    }

    //닉네임 수정하기
    @PostMapping("/member/mypage/nickname")
    @ResponseBody
    public int changeNickname(@RequestParam("nickname") String nickname,  Authentication auth) {
        Member user = (Member) auth.getPrincipal();

        try{
            Member changedUser = memberService.updateNickname(user.getMemberId(), nickname);
            //세션 수정
            memberService.changeSession(changedUser);
        }catch(Exception e){
            //에러 발생
            return 0;
        }
       return 1;
    }

    //비밀번호 수정 폼으로 이동
    @GetMapping("/member/mypage/password")
    public String goChangePassword(){
        return "member/passwordForm";
    }

    //비밀번호 수정하기
    @ResponseBody
    @PostMapping("/member/mypage/password")
    public int changePassword(@RequestParam("originPassword") String originPW, @RequestParam("newPassword") String newPW, Authentication auth) {
        try{
            Member changedUser = memberService.changePassword((Member) auth.getPrincipal(), originPW, newPW);
            memberService.changeSession(changedUser);
        }catch (Exception e){
            //에러발생
            return 0;
        }
        return 1;
    }

}
