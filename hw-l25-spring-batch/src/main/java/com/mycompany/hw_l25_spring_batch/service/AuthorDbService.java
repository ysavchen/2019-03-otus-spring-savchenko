package com.mycompany.hw_l25_spring_batch.service;

import com.mycompany.hw_l25_spring_batch.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    String save(Author author);

    Optional<Author> getById(String id);
}
