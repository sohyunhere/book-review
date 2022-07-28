package com.example.bookreview.user.auth;

import com.example.bookreview.user.model.Member;
import com.example.bookreview.user.repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepo memberRepo;

    @Override// 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 MemberInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        Optional<Member> result = memberRepo.findByMemberEmail(username);
        if(result.isPresent()){
            return new PrincipalDetails(result.get());
        }
        return null;
    }
}