package com.mycompany.hw_l20_spring_webflux.repositories;

import com.mycompany.hw_l20_spring_webflux.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookRepositoryCustomImplTests extends AbstractRepositoryTest {

    private final Book guide = new Book("test-book-1", "A Guide to SQL");

    @Autowired
    private ReactiveMongoOperations operations;

    @Autowired
    private BookRepositoryCustomImpl bookRepositoryCustom;

    @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
    @Test
    void updateTitle() {
        var title = "new title";
        bookRepositoryCustom.updateTitle(guide.getId(), title).block();
        Mono<Book> bookMono = operations.findById(guide.getId(), Book.class);

        StepVerifier
                .create(bookMono)
                .assertNext(book -> {
                    assertNotNull(book);
                    assertEquals(title, book.getTitle());
                })
                .expectComplete()
                .verify();
    }
}
