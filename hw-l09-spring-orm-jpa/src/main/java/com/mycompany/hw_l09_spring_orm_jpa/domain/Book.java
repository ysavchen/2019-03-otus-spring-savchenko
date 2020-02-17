package com.mycompany.hw_l09_spring_orm_jpa.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
@Accessors(fluent = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Genre genre;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(long id, String title) {
        this.id = id;
        this.title = title;
    }
}
