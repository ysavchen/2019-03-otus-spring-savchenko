package com.mycompany.hw_l07_dao_spring_jdbc.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private final long id;
    private final String title;
    private final Author author;
    private final Genre genre;

}
