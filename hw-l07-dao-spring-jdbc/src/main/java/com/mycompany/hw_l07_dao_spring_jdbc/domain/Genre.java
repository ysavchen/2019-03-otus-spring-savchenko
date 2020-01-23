package com.mycompany.hw_l07_dao_spring_jdbc.domain;

import lombok.Data;

import java.util.Set;

@Data
public class Genre {

    private long id;
    private String name;
    private Set<Book> books;

}
