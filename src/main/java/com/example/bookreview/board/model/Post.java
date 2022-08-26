package com.example.bookreview.board.model;

import com.example.bookreview.file.model.AttachedFile;
import com.example.bookreview.location.model.Location;
import com.example.bookreview.user.model.Member;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static org.hibernate.annotations.CascadeType.DELETE_ORPHAN;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Post {

    @Id
    @Column(name = "POST_ID")
    @SequenceGenerator(name = "POST_ID_GENERATOR", sequenceName = "POST_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_ID_GENERATOR")
    private Long postId;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Column(name = "POST_TITLE")
    private String postTitle;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "RBOOK_DATE")
    private Date readBookDate;
    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "WRITTENDATE")
    private Date writtenDate;

    @Column(name = "VIEWCOUNT")
    private Long viewCount;

    private String author;
    private String publisher;

    @OneToOne
    @PrimaryKeyJoinColumn(name="LOCATION_ID")
    private Location location;

    @OneToMany(mappedBy = "post")
    private List<AttachedFile> attachedFileList;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Cascade(DELETE_ORPHAN)
    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    @OrderBy("commentId ASC")
    private List<Comments> comments;

    public Post() {

    }
}
