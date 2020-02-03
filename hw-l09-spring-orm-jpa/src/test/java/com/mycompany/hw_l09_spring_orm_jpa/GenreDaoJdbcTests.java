package com.mycompany.hw_l09_spring_orm_jpa;

import com.mycompany.hw_l09_spring_orm_jpa.dao.GenreDaoJdbc;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(GenreDaoJdbc.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GenreDaoJdbcTests {

    private final Genre genre = new Genre(1, "Computers & Technology");

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    void insertGenre() {
        var genre = new Genre("test");
        long id = genreDaoJdbc.insert(genre);
        assertEquals(2, id, "Invalid id for an inserted Genre");
    }

    @Test
    void insertGenreWithId() {
        var genre = new Genre(100, "test");
        assertEquals(2, genreDaoJdbc.insert(genre),
                "Id of the entity must not be inserted in DB");
    }

    @Test
    void getGenreById() {
        assertThat(genreDaoJdbc.getById(genre.getId())).get()
                .hasFieldOrPropertyWithValue("id", genre.getId())
                .hasFieldOrPropertyWithValue("name", genre.getName());
    }

    @Test
    void getGenreByNonExistingId() {
        assertThat(genreDaoJdbc.getById(50)).isEmpty();
    }
}
