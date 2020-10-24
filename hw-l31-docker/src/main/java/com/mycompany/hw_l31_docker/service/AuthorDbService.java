package com.mycompany.hw_l31_docker.service;

import com.mycompany.hw_l31_docker.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    Optional<Author> getById(long id);
}
