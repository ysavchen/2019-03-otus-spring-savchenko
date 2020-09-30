package com.mycompany.hw_l25_spring_batch.repositories;

import com.mycompany.hw_l25_spring_batch.AbstractRepositoryTest;
import com.mycompany.hw_l25_spring_batch.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AuthorRepositoryTests extends AbstractRepositoryTest {

    private final Author pratt = new Author("Philip", "Pratt").setId("1");
    private static final String NON_EXISTING_ID = "50";

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private MongoOperations operations;

    @Test
    void saveAuthor() {
        var author = new Author("Michael", "Smith");
        String id = repository.save(author).getId();
        assertFalse(id.isEmpty(), "Invalid id for a saved Author");

        assertThat(operations.findById(id, Author.class))
                .hasFieldOrPropertyWithValue("id", author.getId())
                .hasFieldOrPropertyWithValue("name", author.getName())
                .hasFieldOrPropertyWithValue("surname", author.getSurname());
    }

    @Test
    void findAuthorById() {
        assertThat(repository.findById(pratt.getId())).get()
                .hasFieldOrPropertyWithValue("id", pratt.getId())
                .hasFieldOrPropertyWithValue("name", pratt.getName())
                .hasFieldOrPropertyWithValue("surname", pratt.getSurname());
    }

    @Test
    void findAuthorByNonExistingId() {
        assertThat(repository.findById(NON_EXISTING_ID)).isEmpty();
    }
}
