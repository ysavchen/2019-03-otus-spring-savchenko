package com.mycompany.hw_l13_spring_data_nosql.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")
@Accessors(chain = true)
public class Book {

    @Id
    private String id;
    private String title;
    private Genre genre;

    @DBRef
    private Author author;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public Book(String id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
