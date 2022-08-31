package com.example.bookreview.file.model;

import com.example.bookreview.board.model.Post;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
public class AttachedFile implements Serializable {
    @Id
    @Column(name = "FILE_ID")
    @SequenceGenerator(name = "FILE_ID_GENERATOR", sequenceName = "FILE_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_ID_GENERATOR")
    private Long fileId;

    private int fsize;
    private String ftype;
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date writtenDate;

    @ManyToOne
    @JoinColumn(name="POST_ID")
    private Post post;

    public AttachedFile() {

    }
}
