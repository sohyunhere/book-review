package com.example.bookreview.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name="POSTCOUNT")
public class PostCount {

    @SequenceGenerator(name = "POSTCOUNT_ID_GENERATOR", sequenceName = "POSTCOUNT_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTCOUNT_ID_GENERATOR")
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate time;

    @Column(name = "CATEGORY_ID")
    private String category_Id;

    @Column(name = "COUNTPOST")
    private int countPost;


    public PostCount() {

    }
}
