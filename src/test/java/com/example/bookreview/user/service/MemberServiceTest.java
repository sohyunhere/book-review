package com.example.bookreview.user.service;

import com.example.bookreview.user.model.Member;
import com.example.bookreview.user.model.SignupDto;
import com.example.bookreview.user.repo.MemberRepo;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//@AutoConfigureMockMvc
//@Transactional
public class MemberServiceTest {

//    @Mock
    @Autowired
    private static MemberRepo memberRepo;

//    @InjectMocks
    @Autowired
    private MemberService memberService;
//    @AfterClass
//    public static void afterClass(){
//        System.out.println("## AfterAll Annotation 호출 ##");
//        Optional<Member> member = Optional.of(memberRepo.findByMemberEmail("1111@gmail.com").get());
//        Member member1 = member.get();
//        memberRepo.deleteById(member1.getMemberId());
//    }

//    @Test
//    void testMock() {
//        final String email="fdfd@gmail.com";
//        given(memberRepo.findByMemberEmail(any())).willReturn(Optional.of(new Member(1L, email, "password","member-test")));
//
//        Member member = memberService.findMemberByEmail(email);
//
//        assertNotNull(member);
//    }
    @Test
    @Transactional
    public void join() throws Exception {
        //회원가입
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
    @Transactional
    public void 닉네임변경() throws Exception {
        join();
        //닉네임 변경
        Member member1 = memberService.findMemberByEmail("1111@gmail.com");

        Member change = memberService.updateNickname(member1.getMemberId(),"닉네임바꾸기");
        System.out.println("바뀐 닉네임: "+change.getMemberNickname());
        Member member = memberService.findMemberByEmail("1111@gmail.com");
        System.out.println("바뀐 닉네임22: "+member.getMemberNickname());
        assertThat(member1.getMemberEmail()).isEqualTo(change.getMemberEmail());
//        assertFalse(member1.getMemberNickname().equals(change.getMemberNickname()));
        System.out.println("바뀐 닉네임: "+change.getMemberNickname());
    }

}