package com.mycompany.hw_l20_spring_webflux.service;

import com.mycompany.hw_l20_spring_webflux.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    Optional<Author> getById(long id);
}
