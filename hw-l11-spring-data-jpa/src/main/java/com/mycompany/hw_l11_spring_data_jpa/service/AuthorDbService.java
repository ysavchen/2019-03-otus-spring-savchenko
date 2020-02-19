package com.mycompany.hw_l11_spring_data_jpa.service;

import com.mycompany.hw_l11_spring_data_jpa.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    long save(Author author);

    Optional<Author> getById(long id);
}
