package com.example.bookreview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {
    @Id
//    @SequenceGenerator(name = "ROLE_GENERATOR", sequenceName = "ROLE_SEQ", initialValue = 1, allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Member> members;

}
