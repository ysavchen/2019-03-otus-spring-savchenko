package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GenreRepositoryTests {

    private static final Genre GENRE = new Genre(1, "Computers & Technology");
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private GenreRepository repository;

    @Test
    void saveGenre() {
        var genre = new Genre("test");
        long id = repository.save(genre).getId();
        assertEquals(2, id, "Invalid id for an saved Genre");
    }

    @Test
    void findGenreById() {
        assertThat(repository.findById(GENRE.getId())).get()
                .hasFieldOrPropertyWithValue("id", GENRE.getId())
                .hasFieldOrPropertyWithValue("name", GENRE.getName());
    }

    @Test
    void findGenreByNonExistingId() {
        assertThat(repository.findById(NON_EXISTING_ID)).isEmpty();
    }
}
