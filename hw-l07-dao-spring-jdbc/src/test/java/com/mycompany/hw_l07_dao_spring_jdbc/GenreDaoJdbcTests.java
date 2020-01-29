package com.mycompany.hw_l07_dao_spring_jdbc;

import com.mycompany.hw_l07_dao_spring_jdbc.dao.GenreDaoJdbc;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Genre;
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

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    void insertGenre() {
        var genre = new Genre("test");
        long id = genreDaoJdbc.insert(genre);
        assertEquals(2, id, "Invalid id for an inserted Genre");
        assertThat(genreDaoJdbc.getById(id)).get()
                .hasFieldOrPropertyWithValue("name", genre.getName());
    }

    @Test
    void insertGenreWithId() {
        var genre = new Genre(100, "test");
        assertEquals(2, genreDaoJdbc.insert(genre));
    }
}
