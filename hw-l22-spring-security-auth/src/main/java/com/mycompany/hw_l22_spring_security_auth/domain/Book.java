package com.mycompany.hw_l22_spring_security_auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("author"),
                @NamedAttributeNode("genre")
        })
@Accessors(chain = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "genre_id")
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

    public Book(long id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
