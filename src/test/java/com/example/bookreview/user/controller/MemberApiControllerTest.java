package com.example.bookreview.user.controller;

import com.example.bookreview.user.controller.MemberApiController;
import com.example.bookreview.user.model.Member;
import com.example.bookreview.user.model.SigninDto;
import com.example.bookreview.user.service.MemberService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//@WebMvcTest(MemberApiController.class)
public class MemberApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @Test
    public void index_anonymous() throws Exception {
        mockMvc.perform(get("/")
                        .with(anonymous()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void index_user() throws Exception {
        mockMvc.perform(get("/")
                        .with(user("fdfd@gmail.com").roles("USER")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void index_anonymous2() throws Exception {
        mockMvc.perform(get("/member/mypage")
                        .with(anonymous()))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @Transactional
    public void 로그인성공() throws Exception{
        String username = "test";
        String tier="Silver I";
        String email="test@gmail.com";
        String password = "12345";

        SigninDto member=new SigninDto();
        (username, tier, email, password);
        memberService.join(member);
        // 성공 TEST
        mockMvc.perform(formLogin().user(member.getName()).password(password))
                .andDo(print())
                .andExpect(authenticated());
    }

//    @Test
//    public void index_user2() throws Exception {
//        mockMvc.perform(get("/member/mypage")
//                        .with(user("fdfd@gmail.com").roles("USER")))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//    @Test
//    @DisplayName("member 가져오기")
//    void getMemberTest() throws Exception{
//        given(memberService.findMemberByEmail("fdfd@gmail.com")).willReturn(
//                new Member(123L, "fdfd@gmail.com", "thgus!234", "목")
//        );
//        Long memberId = 234L;
//
//        mockMvc.perform(
//                get(""))
//                .andExpect(status.ok())
//                .andExpectAll(jsonPath())
//    }
}
