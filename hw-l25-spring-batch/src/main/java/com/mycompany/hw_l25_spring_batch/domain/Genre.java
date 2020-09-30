package com.mycompany.hw_l25_spring_batch.domain;

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
}
