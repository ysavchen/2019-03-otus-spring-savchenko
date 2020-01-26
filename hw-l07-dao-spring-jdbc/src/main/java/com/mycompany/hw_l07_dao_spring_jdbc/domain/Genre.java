package com.mycompany.hw_l07_dao_spring_jdbc.domain;

import lombok.Data;

@Data
public class Genre {

    private long id;
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }
}
