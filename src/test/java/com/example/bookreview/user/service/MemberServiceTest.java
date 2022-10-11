package com.example.bookreview.user.service;

import com.example.bookreview.user.model.Member;
import com.example.bookreview.user.model.SignupDto;
import com.example.bookreview.user.repo.MemberQueryRepo;
import com.example.bookreview.user.repo.MemberRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class MemberServiceTest {

    @Mock
    private MemberRepo memberRepo;

    @Mock
    private MemberQueryRepo memberQueryRepo;

//    @InjectMocks
    @Autowired
    private MemberService memberService;


    @Test
    void testMock() {
        final String email="fdfd@gmail.com";
        given(memberRepo.findByMemberEmail(any())).willReturn(Optional.of(new Member(1L, email, "password","member-test")));

        Member member = memberService.findMemberByEmail(email);

        assertNotNull(member);
    }
    @Test
    void join() throws Exception {
        SignupDto signupDto = new SignupDto();
        signupDto.setPassword("yesyes");
        signupDto.setPassword2("yesyes");
        signupDto.setEmail("1111@gmail.com");
        signupDto.setNickname("테스트");
        memberService.join(signupDto);

        Member member = memberService.findMemberByEmail(signupDto.getEmail());
        assertThat(signupDto.getNickname()).isEqualTo(member.getMemberNickname());
        assertThat(signupDto.getPassword()).isEqualTo(member.getPassword());
        System.out.println("닉네임:"+ member.getMemberNickname());
    }

    @Test
    void updateNickname(){
        Member member = memberService.findMemberByEmail("1111@gmail.com");
        System.out.println("아이디: " + member.getMemberId());
        Member change = memberService.updateNickname(member.getMemberId(),"닉네임바꾸기");
        System.out.println("바뀐 닉네임: "+change.getMemberNickname());
        assertThat(member.getMemberEmail()).isEqualTo(change.getMemberEmail());
        assertFalse(member.getMemberNickname().equals(change.getMemberNickname()));
        System.out.println("바뀐 닉네임: "+change.getMemberNickname());

    }
}