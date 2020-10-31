package com.mycompany.hw_l34_srping_cloud.service;

import com.mycompany.hw_l34_srping_cloud.domain.Author;

import java.util.Optional;

public interface AuthorDbService {

    Optional<Author> getById(long id);
}
