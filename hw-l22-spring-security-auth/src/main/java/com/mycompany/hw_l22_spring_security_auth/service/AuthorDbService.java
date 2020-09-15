package com.mycompany.hw_l22_spring_security_auth.service;

import com.mycompany.hw_l22_spring_security_auth.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    Optional<Author> getById(long id);
}
