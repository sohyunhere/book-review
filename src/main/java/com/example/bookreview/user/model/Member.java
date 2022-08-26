package com.example.bookreview.user.model;

import com.example.bookreview.board.model.Comments;
import com.example.bookreview.board.model.Post;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static org.hibernate.annotations.CascadeType.DELETE_ORPHAN;


@Entity
@Builder
@Getter
@AllArgsConstructor
@Table(name="MEMBERINFO")
public class Member implements UserDetails, Serializable{
    private static final long serialVersionUID = 174726374856727L;

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

    private String role;

    @OneToMany(mappedBy = "member")
    @OrderBy("postId asc ")
    private List<Post> posts;

    @OneToMany(mappedBy = "member",fetch=FetchType.EAGER)
    @OrderBy("commentId desc ")
    private List<Comments> comments;

    public Member() {

    }
    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role));
        return roles;
    }

    @Override
    public String getPassword() {
        return this.getMemberPassword();
    }

    @Override
    public String getUsername() {
        return this.getMemberEmail();
    }
    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않았음
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
    }
}