package com.example.bookreview.board.model;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Table(name="CATEGORY")
public class Category  implements Serializable {
    @Id
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Post> postList;

    public Category() {

    }
}
