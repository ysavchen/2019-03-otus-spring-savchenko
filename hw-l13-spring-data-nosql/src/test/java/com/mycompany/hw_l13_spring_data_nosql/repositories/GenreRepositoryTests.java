package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.AbstractRepositoryTest;
import com.mycompany.hw_l13_spring_data_nosql.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GenreRepositoryTests extends AbstractRepositoryTest {

    private final Genre genre = new Genre( "Computers & Technology");
    private static final String NON_EXISTING_ID = "50";

    @Autowired
    private GenreRepository repository;

    @Test
    void saveGenre() {
        var genre = new Genre("test");
        String id = repository.save(genre).getId();
        assertFalse(id.isEmpty(), "Invalid id for an saved Genre");
    }

    @Test
    void findGenreById() {
        assertThat(repository.findById(genre.getId())).get()
                .hasFieldOrPropertyWithValue("id", genre.getId())
                .hasFieldOrPropertyWithValue("name", genre.getName());
    }

    @Test
    void findGenreByNonExistingId() {
        assertThat(repository.findById(NON_EXISTING_ID)).isEmpty();
    }
}
