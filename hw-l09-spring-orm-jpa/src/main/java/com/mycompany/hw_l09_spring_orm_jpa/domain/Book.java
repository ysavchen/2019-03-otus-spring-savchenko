package com.mycompany.hw_l09_spring_orm_jpa.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Book {

    private long id;
    private String title;
    private Author author;
    private Genre genre;

    public Book(String title) {
        this.title = title;
    }

    public Book(long id, String title) {
        this.id = id;
        this.title = title;
    }
}
