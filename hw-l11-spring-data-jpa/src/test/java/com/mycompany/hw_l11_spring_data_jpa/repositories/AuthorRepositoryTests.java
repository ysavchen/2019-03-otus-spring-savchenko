package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthorRepositoryTests {

    private final Author author = new Author(1, "Philip", "Pratt");
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private AuthorRepository repository;

    @Test
    void insertAuthor() {
        var author = new Author("Michael", "Smith");
        long id = repository.save(author).id();
        assertEquals(3, id, "Invalid id for an inserted Author");
        assertThat(repository.findById(id)).get()
                .hasFieldOrPropertyWithValue("name", author.name())
                .hasFieldOrPropertyWithValue("surname", author.surname());
    }

    @Test
    void getAuthorById() {
        assertThat(repository.findById(author.id())).get()
                .hasFieldOrPropertyWithValue("id", author.id())
                .hasFieldOrPropertyWithValue("name", author.name())
                .hasFieldOrPropertyWithValue("surname", author.surname());
    }

    @Test
    void getAuthorByNonExistingId() {
        assertThat(repository.findById(NON_EXISTING_ID)).isEmpty();
    }
}
