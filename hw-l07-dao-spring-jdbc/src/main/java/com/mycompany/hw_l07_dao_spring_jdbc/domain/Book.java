package com.mycompany.hw_l07_dao_spring_jdbc.domain;

import lombok.Data;

@Data
public class Book {

    private long id;
    private String title;
    private Author author;
    private Genre genre;

}
