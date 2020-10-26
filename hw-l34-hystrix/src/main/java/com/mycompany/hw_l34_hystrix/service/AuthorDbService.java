package com.mycompany.hw_l34_hystrix.service;

import com.mycompany.hw_l34_hystrix.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    Optional<Author> getById(long id);
}
