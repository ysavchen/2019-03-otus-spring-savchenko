package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataMongoTest
public class AuthorRepositoryTests {

    private final Author author = new Author("1", "Philip", "Pratt");
    private static final String NON_EXISTING_ID = "50";

    @Autowired
    private AuthorRepository repository;

    @Test
    void saveAuthor() {
        var author = new Author("Michael", "Smith");
        String id = repository.save(author).getId();
        assertFalse(id.isEmpty(), "Invalid id for an saved Author");

        assertThat(repository.findById(id)).get()
                .hasFieldOrPropertyWithValue("name", author.getName())
                .hasFieldOrPropertyWithValue("surname", author.getSurname());
    }

    @Test
    void findAuthorById() {
        assertThat(repository.findById(author.getId())).get()
                .hasFieldOrPropertyWithValue("id", author.getId())
                .hasFieldOrPropertyWithValue("name", author.getName())
                .hasFieldOrPropertyWithValue("surname", author.getSurname());
    }

    @Test
    void findAuthorByNonExistingId() {
        assertThat(repository.findById(NON_EXISTING_ID)).isEmpty();
    }
}
