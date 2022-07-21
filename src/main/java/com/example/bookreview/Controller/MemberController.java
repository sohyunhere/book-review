package com.example.bookreview.Controller;

import com.example.bookreview.Message;
import com.example.bookreview.domain.Member;
import com.example.bookreview.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/member/register")
    public String goSignup(){
        return "member/signupForm";
    }

    @PostMapping("/member/register")
    public ModelAndView register(@Valid signupForm form, ModelAndView mav){
        Member member = new Member();
        member.setMemberEmail(form.getEmail());
        member.setMemberPassword(form.getPassword());
        member.setMemberNickname(form.getEmail());
        if(!(form.getPassword().equals(form.getPassword2()))){
            mav.addObject("data", new Message("비밀번호가 서로 다릅니다", "/member/register"));
            mav.setViewName("message");
            return mav;
        }

        if(memberService.join(member) == -1L){
            mav.addObject("data", new Message("중복된 이메일이 존재합니다", "/member/register"));
            mav.setViewName("message");
            return mav;
        }
        mav.setViewName("main");
        return mav;
    }

}
