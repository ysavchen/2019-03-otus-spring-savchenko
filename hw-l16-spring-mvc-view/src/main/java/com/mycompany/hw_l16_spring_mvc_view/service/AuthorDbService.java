package com.mycompany.hw_l16_spring_mvc_view.service;

import com.mycompany.hw_l16_spring_mvc_view.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    Optional<Author> getById(long id);
}
