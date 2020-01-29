package com.mycompany.hw_l07_dao_spring_jdbc.dao;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Genre;

import java.util.Optional;

public interface GenreDao {

    long insert(Genre genre);

    Optional<Genre> getById(long id);
}
