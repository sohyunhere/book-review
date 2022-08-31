package com.example.bookreview.board.model;

import com.example.bookreview.file.model.AttachedFile;
import com.example.bookreview.location.model.Location;
import com.example.bookreview.user.model.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Post  implements Serializable {
    private static final long serialVersionUID = 1260627612961915463L;
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

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<AttachedFile> attachedFileList;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderBy("commentId ASC")
    @JsonIgnore
    private List<Comments> comments;

    public Post() {

    }
}
