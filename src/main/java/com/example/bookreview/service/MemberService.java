package com.example.bookreview.service;

import com.example.bookreview.domain.Member;
import com.example.bookreview.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepo memberRepo;
    @Autowired
    public MemberService(MemberRepo memberRepo){
        this.memberRepo = memberRepo;
    }

    //회원가입

    public Long join(Member member){
        Optional<Member> result = memberRepo.findByMemberEmail(member.getMemberEmail());
        if(result.isPresent()){
            return -1L;
        }
//        if(member.getMemberPassword() != mem)
        memberRepo.save(member);
        return member.getMemberId();
    }

    //로그인
    //전체 회원 조회
}
