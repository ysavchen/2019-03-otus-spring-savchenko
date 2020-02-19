package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Genre;
import com.mycompany.hw_l11_spring_data_jpa.repositories.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GenreRepositoryTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private GenreRepository repository;

    @Test
    void insertGenre() {
        var genre = new Genre("test");
        long id = repository.save(genre).id();
        assertEquals(2, id, "Invalid id for an inserted Genre");
    }

    @Test
    void getGenreById() {
        assertThat(repository.findById(genre.id())).get()
                .hasFieldOrPropertyWithValue("id", genre.id())
                .hasFieldOrPropertyWithValue("name", genre.name());
    }

    @Test
    void getGenreByNonExistingId() {
        assertThat(repository.findById(NON_EXISTING_ID)).isEmpty();
    }
}
