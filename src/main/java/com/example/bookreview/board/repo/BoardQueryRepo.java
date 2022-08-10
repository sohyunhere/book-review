package com.example.bookreview.board.repo;

import com.example.bookreview.board.model.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.example.bookreview.board.model.QPost.post;

@Repository
public class BoardQueryRepo extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param jpaQueryFactory must not be {@literal null}.
     */
    public BoardQueryRepo(JPAQueryFactory jpaQueryFactory) {
        super(Post.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public void updateView(Long id, Long viewCount){
        jpaQueryFactory
                .update(post)
                .set(post.viewCount, viewCount)
                .where(post.postId.eq(id))
                .execute();
    }
}
