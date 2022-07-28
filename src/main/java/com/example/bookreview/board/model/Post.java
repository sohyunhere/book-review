package com.example.bookreview.board.model;

import com.example.bookreview.file.model.AttachedFile;
import com.example.bookreview.location.model.Location;
import com.example.bookreview.user.model.Member;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Post {

    @Id
    @Column(name = "POST_ID")
    @SequenceGenerator(name = "POST_ID_GENERATOR", sequenceName = "POST_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_ID_GENERATOR")
    private Long postId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

//    @Column(name = "MEMBER_ID")
//    private Long memberId;

//    @Column(name = "CATEGORY_ID")
//    private Long categoryId;
    @Column(name = "POST_TITLE")
    private String postTitle;
    @Column(name = "RBOOK_DATE")
    private Date readBookDate;
    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date writtenDate;

    private int viewCount;

//    @Column(name = "LOCATION_ID")
//    private Long locationId;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name="LOCATION_ID")
    private Location location;

    @OneToMany(mappedBy = "post")
    private List<AttachedFile> attachedFileList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "post")
    private List<Comments> comments;

    public Post() {

    }
}
