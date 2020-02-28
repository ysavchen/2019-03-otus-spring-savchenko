package com.mycompany.hw_l07_dao_spring_jdbc.service;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    long insert(Author author);

    Optional<Author> getById(long id);
}
