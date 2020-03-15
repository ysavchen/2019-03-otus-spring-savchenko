package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;

public interface BookRepositoryCustom {

    Book saveWithAuthor(Book book);
}
