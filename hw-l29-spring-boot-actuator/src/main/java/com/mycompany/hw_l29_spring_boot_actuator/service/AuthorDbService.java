package com.mycompany.hw_l29_spring_boot_actuator.service;

import com.mycompany.hw_l29_spring_boot_actuator.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    Optional<Author> getById(long id);
}
