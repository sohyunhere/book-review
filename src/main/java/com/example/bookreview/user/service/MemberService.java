package com.example.bookreview.user.service;

import com.example.bookreview.user.model.Member;
import com.example.bookreview.user.model.SignupDto;
import com.example.bookreview.user.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService  {

    private final MemberRepo memberRepo;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public boolean join(SignupDto signupDto){
        Optional<Member> result = memberRepo.findByMemberEmail(signupDto.getEmail());
        //이메일 중복확인
        if(result.isEmpty()){
            //이메일 중복 아님
            if(!(signupDto.getPassword().equals(signupDto.getPassword2()))){
                //비밀번호 서로 틀림
                return false;
            }
            else{
                String encodedPassword = passwordEncoder.encode(signupDto.getPassword());
                signupDto.setPassword(encodedPassword);

                Member saveMember = signupDto.toEntity();
                memberRepo.save(saveMember);
                return true;
            }
        }
        return false;
    }

    //회원 id에 대한 회원 조회
    public Member findMemberById(Long id){
        Optional<Member> result = memberRepo.findByMemberId(id);
        if(result.isEmpty()){
            System.out.println("아이디에 해당하는 멤버가 없음/n");
        }
        return result.get();
    }

    //전체 회원 조회

    //회원 email에 대한 회원 조회
    public Member findMemberByEmail(String email){
        Optional<Member> result = memberRepo.findByMemberEmail(email);
        if(result.isEmpty()){
            System.out.println("email에 해당하는 멤버가 없음/n");
        }
        return result.get();
    }
}
