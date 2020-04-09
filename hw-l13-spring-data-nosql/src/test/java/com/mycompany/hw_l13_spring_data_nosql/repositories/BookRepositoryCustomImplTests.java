package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.AbstractRepositoryTest;
import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryCustomImplTests extends AbstractRepositoryTest {

    private final Book guide = new Book("A Guide to SQL").setId("1");

    @Autowired
    private MongoOperations operations;

    @Autowired
    private BookRepositoryCustomImpl bookRepositoryCustom;

    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    @Test
    void updateTitle() {
        var title = "new title";
        bookRepositoryCustom.updateTitle(guide.getId(), title);
        Book book = operations.findById(guide.getId(), Book.class);
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo(title);
    }
}
