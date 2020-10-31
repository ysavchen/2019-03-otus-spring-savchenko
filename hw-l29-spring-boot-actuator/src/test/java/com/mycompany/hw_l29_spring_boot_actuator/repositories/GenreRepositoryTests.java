package com.mycompany.hw_l29_spring_boot_actuator.repositories;

import com.mycompany.hw_l29_spring_boot_actuator.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class GenreRepositoryTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private GenreRepository repository;

    @Test
    void saveGenre() {
        var genre = new Genre("test");
        long id = repository.save(genre).getId();
        assertTrue(id > 0, "Invalid id for an saved Genre");
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
