package com.mycompany.hw_l09_spring_orm_jpa.domain;

import lombok.Data;

@Data
public class Genre {

    private long id;
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
