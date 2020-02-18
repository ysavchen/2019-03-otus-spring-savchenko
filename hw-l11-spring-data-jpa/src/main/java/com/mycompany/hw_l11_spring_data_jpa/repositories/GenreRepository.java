package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Genre;

import java.util.Optional;

public interface GenreRepository {

    long insert(Genre genre);

    Optional<Genre> getById(long id);
}
