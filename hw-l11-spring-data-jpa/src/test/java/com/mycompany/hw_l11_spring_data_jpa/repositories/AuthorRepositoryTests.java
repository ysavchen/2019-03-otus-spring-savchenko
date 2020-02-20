package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthorRepositoryTests {

    private static final Author AUTHOR = new Author(1, "Philip", "Pratt");
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void saveAuthor() {
        var author = new Author("Michael", "Smith");
        long id = repository.save(author).getId();
        assertEquals(3, id, "Invalid id for an saved Author");

        em.clear();
        assertThat(repository.findById(id)).get()
                .hasFieldOrPropertyWithValue("name", author.getName())
                .hasFieldOrPropertyWithValue("surname", author.getSurname());
    }

    @Test
    void findAuthorById() {
        assertThat(repository.findById(AUTHOR.getId())).get()
                .hasFieldOrPropertyWithValue("id", AUTHOR.getId())
                .hasFieldOrPropertyWithValue("name", AUTHOR.getName())
                .hasFieldOrPropertyWithValue("surname", AUTHOR.getSurname());
    }

    @Test
    void findAuthorByNonExistingId() {
        assertThat(repository.findById(NON_EXISTING_ID)).isEmpty();
    }
}
