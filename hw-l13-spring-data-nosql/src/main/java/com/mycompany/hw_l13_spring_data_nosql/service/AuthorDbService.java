package com.mycompany.hw_l13_spring_data_nosql.service;

import com.mycompany.hw_l13_spring_data_nosql.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    long save(Author author);

    Optional<Author> getById(long id);
}
