package com.mycompany.hw_l07_dao_spring_jdbc.domain;

import lombok.Data;

@Data
public class Author {

    private long id;
    private String name;
    private String surname;

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
