package com.example.bookreview.Controller;

import com.example.bookreview.Message;
import com.example.bookreview.domain.Member;
import com.example.bookreview.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
                                 @Validated @ModelAttribute signupForm form, ModelAndView mav){
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
        else {

            mav.addObject("data", new Message("회원가입이 완료되었습니다", "/"));
            mav.setViewName("message");
            Member member1 = memberService.findMemberById(id);
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", member1);
            return mav;
        }
    }

    //로그인 폼으로 이동
    @GetMapping("/member/login")
    public String goSignin(){
        return "member/signinForm";
    }
    //로그인
    @PostMapping("/member/login")
    public ModelAndView login(HttpServletRequest request, singinForm form, ModelAndView mav){
        Member member = new Member();
        member.setMemberEmail(form.getEmail());
        member.setMemberPassword(form.getPassword());

        if(memberService.login(member)) {
            Member loginUser = memberService.findMemberByEmail(member.getMemberEmail());
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);

            mav.addObject("data", new Message("로그인이 완료되었습니다", "/"));
            mav.setViewName("message");

        }else{
            mav.addObject("data", new Message("이메일 혹은 비밀번호가 틀립니다", "/member.login"));
            mav.setViewName("message");
        }
       return mav;
    }
    //로그아웃
    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        return "main";
    }
}
