package com.example.bookreview.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name="MEMBERINFO")
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    @SequenceGenerator(name = "MEMBER_NO_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_NO_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;
    @Column(name = "MEMBER_PASSWORD")
    private String memberPassword;
    @Column(name = "MEMBER_NICKNAME")
    private String memberNickname;

   @OneToMany(mappedBy = "member")
    private List<Post> postList;

   @OneToMany(mappedBy = "member")
   private List<Comments> commentsList;
    public Member() {

    }

    public Member(String memberEmail, String memberPassword, String memberNickname) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberNickname='" + memberNickname + '\'' +
                '}';
    }
}