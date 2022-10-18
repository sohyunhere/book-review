package com.example.bookreview.board.controller;

import com.example.bookreview.board.service.BoardService;
import com.example.bookreview.user.model.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BoardControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    BoardService boardService;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void open_post_page() throws Exception {

        String nickname ="소소";
        String email="test@gmail.com";
        String password = "12345";

        Member member = new Member(100L, email, password, nickname, "ROLE_USER");

        mockMvc.perform(get("/board/1")
                     .with(user(member)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void page_category() throws Exception {

        String nickname ="소소";
        String email="test@gmail.com";
        String password = "12345";

        Member member = new Member(100L, email, password, nickname, "ROLE_USER");

        mockMvc.perform(post("/category/post/1")
                        .with(user(member))
                .contentType(MediaType.APPLICATION_JSON))//본문 요청에 json을 담아서 보내고 있다고 알려줌.
//                .accept(MediaTypes.HAL_JSON)//HAL_JSON으로 받는다.
//                .content(objectMapper.writeValueAsString(event)))//요청 본문에 json으로 변환후 넣어준다
            .andDo(print())//어떤 응답과 요청을 받았는지 확인가능.
//                .andExpect(status().isCreated());//201요청이 들어왔는지?
                .andExpect(status().isOk());
    }
}
