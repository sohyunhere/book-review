package com.example.bookreview.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class PostCount {
    @Id
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate time;

    private String category_Id;
    private int countPost;


    public PostCount() {

    }
}
