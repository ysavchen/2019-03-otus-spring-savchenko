package com.mycompany.hw_l09_spring_orm_jpa.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
@Accessors(fluent = true)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment() {
    }

    public Comment(String content) {
        this.content = content;
    }
}
