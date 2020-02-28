package com.mycompany.hw_l09_spring_orm_jpa.repositories;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Author;
import com.mycompany.hw_l09_spring_orm_jpa.repositories.AuthorRepositoryImpl;
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
@Import(AuthorRepositoryImpl.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthorRepositoryImplTests {

    private final Author author = new Author(1, "Philip", "Pratt");
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private AuthorRepositoryImpl authorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void insertAuthor() {
        var author = new Author("Michael", "Smith");
        long id = authorRepository.insert(author);
        assertEquals(3, id, "Invalid id for an inserted Author");
        assertThat(authorRepository.getById(id)).get()
                .hasFieldOrPropertyWithValue("name", author.name())
                .hasFieldOrPropertyWithValue("surname", author.surname());
    }

    @Test
    void getAuthorById() {
        assertThat(authorRepository.getById(author.id())).get()
                .hasFieldOrPropertyWithValue("id", author.id())
                .hasFieldOrPropertyWithValue("name", author.name())
                .hasFieldOrPropertyWithValue("surname", author.surname());
    }

    @Test
    void getAuthorByNonExistingId() {
        assertThat(authorRepository.getById(NON_EXISTING_ID)).isEmpty();
    }
}
