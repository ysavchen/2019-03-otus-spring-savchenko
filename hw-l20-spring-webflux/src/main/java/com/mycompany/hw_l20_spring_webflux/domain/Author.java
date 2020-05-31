package com.mycompany.hw_l20_spring_webflux.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authors")
@Accessors(chain = true)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    public Author() {
    }

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
