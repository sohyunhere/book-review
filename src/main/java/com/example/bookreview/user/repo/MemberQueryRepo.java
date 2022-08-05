package com.example.bookreview.user.repo;

import com.example.bookreview.user.model.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.bookreview.user.model.QMember.member;

@Repository
public class MemberQueryRepo extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    private final PasswordEncoder passwordEncoder;
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param jpaQueryFactory
     */
    public MemberQueryRepo(JPAQueryFactory jpaQueryFactory, PasswordEncoder passwordEncoder) {
        super(Member.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<Member> findByMemberId(Long id){
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(member)
                .where(member.memberId.eq(id))
                .fetchOne());
    }
    public void updateNickname(Long id, String nickName){
        jpaQueryFactory
                .update(member)
                .set(member.memberNickname, nickName)
                .where(member.memberId.eq(id))
                .execute();
    }

    public void updatePassword(Long id, String password){
        String encodedPassword = passwordEncoder.encode(password);

        jpaQueryFactory
                .update(member)
                .set(member.memberPassword, encodedPassword)
                .where(member.memberId.eq(id))
                .execute();
    }
}
