package com.example.bookreview.board.model;

import com.example.bookreview.board.model.Post;
import com.example.bookreview.user.model.Member;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Comments {
   @Id
   @Column(name = "COMMENT_ID")
   @SequenceGenerator(name = "COMMENT_ID_GENERATOR", sequenceName = "COMMENT_SEQ", initialValue = 1, allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_ID_GENERATOR")
   private Long commentId;

   private String content;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "WRITTENDATE")
   private Date writtenDate;

   @ManyToOne
   @JoinColumn(name = "POST_ID")
   private Post post;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Comments() {

    }
}
