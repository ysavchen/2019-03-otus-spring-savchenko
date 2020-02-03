package com.mycompany.hw_l09_spring_orm_jpa.repositories;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Genre;

import java.util.Optional;

public interface GenreRepository {

    long insert(Genre genre);

    Optional<Genre> getById(long id);
}
