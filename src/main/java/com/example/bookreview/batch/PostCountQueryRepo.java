package com.example.bookreview.batch;

import com.example.bookreview.batch.model.PostCount;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

import static com.example.bookreview.batch.model.QPostCount.postCount;

@Repository
public class PostCountQueryRepo extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public PostCountQueryRepo(JPAQueryFactory jpaQueryFactory) {
        super(PostCount.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public void selectCount(String name, Date time){
        jpaQueryFactory
                .selectFrom(postCount)
                .where(postCount.categoryName.eq(name)
                .and(postCount.time.eq((Expression<? super LocalDate>) time)))
                .fetchOne();
    }
}
