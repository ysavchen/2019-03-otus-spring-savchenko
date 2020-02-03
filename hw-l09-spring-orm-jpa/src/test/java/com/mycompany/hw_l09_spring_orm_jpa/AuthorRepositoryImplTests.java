package com.mycompany.hw_l09_spring_orm_jpa;

import com.mycompany.hw_l09_spring_orm_jpa.dao.AuthorRepositoryImpl;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(AuthorRepositoryImpl.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthorRepositoryImplTests {

    private final Author author = new Author(1, "Philip", "Pratt");

    @Autowired
    private AuthorRepositoryImpl authorDaoJdbc;

    @Test
    void insertAuthor() {
        var author = new Author("Michael", "Smith");
        long id = authorDaoJdbc.insert(author);
        assertEquals(2, id, "Invalid id for an inserted Author");
        assertThat(authorDaoJdbc.getById(id)).get()
                .hasFieldOrPropertyWithValue("name", author.getName())
                .hasFieldOrPropertyWithValue("surname", author.getSurname());
    }

    @Test
    void insertAuthorWithId() {
        var author = new Author(100, "Michael", "Smith");
        assertEquals(2, authorDaoJdbc.insert(author));
    }

    @Test
    void getAuthorById() {
        assertThat(authorDaoJdbc.getById(author.getId())).get()
                .hasFieldOrPropertyWithValue("id", author.getId())
                .hasFieldOrPropertyWithValue("name", author.getName())
                .hasFieldOrPropertyWithValue("surname", author.getSurname());
    }

    @Test
    void getAuthorByNonExistingId() {
        assertThat(authorDaoJdbc.getById(50)).isEmpty();
    }
}
