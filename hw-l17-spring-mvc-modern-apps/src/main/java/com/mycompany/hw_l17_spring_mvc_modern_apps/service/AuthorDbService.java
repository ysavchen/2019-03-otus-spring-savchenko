package com.mycompany.hw_l17_spring_mvc_modern_apps.service;

import com.mycompany.hw_l17_spring_mvc_modern_apps.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    Optional<Author> getById(long id);
}
