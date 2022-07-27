package com.example.bookreview.service;

import com.example.bookreview.domain.Member;
import com.example.bookreview.domain.Role;
import com.example.bookreview.dto.SigninDto;
import com.example.bookreview.dto.SignupDto;
import com.example.bookreview.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService  {

    private final MemberRepo memberRepo;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public MemberService(MemberRepo memberRepo, PasswordEncoder passwordEncoder){
        this.memberRepo = memberRepo;
        this.passwordEncoder = passwordEncoder;
    }

    //회원가입
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
                signupDto.setEnabled(true);

                Member saveMember = signupDto.toEntity();
                Role role = new Role();
                role.setId(1L);
                System.out.println(saveMember.isEnabled());//true
                System.out.println(saveMember.getRoles());//[]
                saveMember.getRoles().add(role);

                Member member = memberRepo.save(saveMember);
                return true;
            }
        }
        return false;
    }

    //회원 id에 대한 회원 조회
    public Member findMemberById(Long id){
        System.out.println(id);
        Optional<Member> result = memberRepo.findByMemberId(id);
        if(result.isEmpty()){
            System.out.println("아이디에 해당하는 멤버가 없음/n");
        }
        return result.get();
    }
    //로그인
    public boolean login(SigninDto signinDto){
        Optional<Member> result = memberRepo.findByMemberEmail(signinDto.getEmail());
        if(result.isEmpty()){
            return false;
        }
        if(!(result.get().getMemberPassword().equals(signinDto.getPassword()))){
            return false;
        }
        return true;
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
