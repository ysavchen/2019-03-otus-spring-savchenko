package com.mycompany.hw_l09_spring_orm_jpa.dao;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Genre;

import java.util.Optional;

public interface GenreDao {

    long insert(Genre genre);

    Optional<Genre> getById(long id);
}
