package com.example.bookreview.user.service;

import com.example.bookreview.user.model.Member;
import com.example.bookreview.user.model.SignupDto;
import com.example.bookreview.user.repo.MemberQueryRepo;
import com.example.bookreview.user.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepo memberRepo;
    private final MemberQueryRepo memberQueryRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override// 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 MemberInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        Optional<Member> result = memberRepo.findByMemberEmail(username);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    //회원가입
    @Transactional
    public boolean join(SignupDto signupDto){
        Optional<Member> result = memberRepo.findByMemberEmail(signupDto.getEmail());
        //이메일 중복확인
        if(result.isEmpty()){
            //이메일 중복 아님
                String encodedPassword = passwordEncoder.encode(signupDto.getPassword());
                signupDto.setPassword(encodedPassword);

                Member saveMember = signupDto.toEntity();
                memberRepo.save(saveMember);
                return true;
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

    //닉네임 수정
    public Long updateNickname(Long id, String name){
        Optional<Member> result = Optional.ofNullable(memberRepo.findByMemberId(id).orElseThrow(() -> {
            return new IllegalArgumentException("회원 찾기 실패");
        }));

        if(result.isPresent()){
            Member member = result.get();
            member.setMemberNickname(name);
            memberRepo.save(member);

            System.out.println("email"+ member.getMemberEmail());
            System.out.println("pass"+ member.getMemberPassword());
            Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(member.getMemberEmail(),
                                    member.getMemberPassword())
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("dkdlel: "+ member.getMemberId());
            return member.getMemberId();
        }
        return -1L;
    }

    @Transactional
    //비밀번호 수정
    public Long changePassword (Member member, String originPW, String newPW) {
       if(passwordEncoder.matches(originPW, member.getMemberPassword())){
           //true
           memberQueryRepo.updatePassword(member.getMemberId(), newPW);
           return member.getMemberId();
       }else{
           return -1L;
       }

    }
}
