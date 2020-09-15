package com.mycompany.hw_l20_spring_webflux.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
public class Author {

    @Id
    private String id;
    private String name;
    private String surname;

    public Author() {
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
