package com.example.bookreview.user.model;
;
import com.example.bookreview.board.model.Post;
import com.example.bookreview.board.model.Comments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.*;


@Entity
@Builder
@Data
@AllArgsConstructor
@Table(name="MEMBERINFO")
public class Member{
    @Id
    @Column(name = "MEMBER_ID")
    @SequenceGenerator(name = "MEMBER_NO_GENERATOR", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_NO_GENERATOR")
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

    private String role;

    public Member() {

    }
}