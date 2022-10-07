package com.example.bookreview.user.service;

import com.example.bookreview.user.repo.MemberRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepo memberRepo;

    @Test
    void loadUserByUsername() {
    }

    @Test
    void join() {
    }

    @Test
    void findMemberByEmail() {
    }

    @Test
    void updateNickname() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void changeSession() {
    }
}