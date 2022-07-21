package com.example.bookreview.Controller;

import com.example.bookreview.Message;
import com.example.bookreview.domain.Member;
import com.example.bookreview.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
                                 @Valid signupForm form, ModelAndView mav){
        Member member = new Member();
        member.setMemberEmail(form.getEmail());
        member.setMemberPassword(form.getPassword());
        member.setMemberNickname(form.getNickname());
        if(!(form.getPassword().equals(form.getPassword2()))){
            mav.addObject("data", new Message("비밀번호가 서로 다릅니다", "/member/register"));
            mav.setViewName("message");
            return mav;
        }
        Long id = memberService.join(member);
        if(id == -1L){
            mav.addObject("data", new Message("중복된 이메일이 존재합니다", "/member/register"));
            mav.setViewName("message");
            return mav;
        }
        mav.setViewName("main");
        Member member1 = memberService.findMemberById(id);
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", member1);
        return mav;
    }

    //로그인 폼으로 이동
    //로그인
    //로그아웃
}
