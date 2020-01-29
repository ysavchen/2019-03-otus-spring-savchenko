package com.mycompany.hw_l07_dao_spring_jdbc.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Author {

    private long id;
    private String name;
    private String surname;
    private final Set<Book> books = new HashSet<>();

    public Author() {
    }

    public Author(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author addBook(Book book) {
        books.add(book);
        return this;
    }
}
