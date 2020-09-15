package com.mycompany.hw_l20_spring_webflux.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Genre {

    private String id;
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
