package com.mycompany.hw_l07_dao_spring_jdbc.dao;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Genre;

public interface GenreDao {

    long insert(Genre genre);

    Genre getById(long id);

    void deleteById(long id);
}
