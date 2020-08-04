package com.mycompany.hw_l20_spring_webflux.repositories;

import com.mycompany.hw_l20_spring_webflux.domain.Author;
import com.mycompany.hw_l20_spring_webflux.domain.Book;
import com.mycompany.hw_l20_spring_webflux.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryImplTests extends AbstractRepositoryTest {

    private final Genre genre = new Genre("test-genre-1", "Computers & Technology");
    private final Author prattAuthor = new Author("test-author-1", "Philip", "Pratt");
    private final Author learnAuthor = new Author("test-authro-2", "Michael", "Learn");

    private final Book guideBook = new Book("test-book-1", "A Guide to SQL", prattAuthor, genre);
    private final Book conceptsBook = new Book("test-book-2", "Concepts of Database Management", prattAuthor, genre);
    private final Book sqlCodingBook = new Book("test-book-3", "SQL Programming and Coding", learnAuthor, genre);

    @Autowired
    private ReactiveMongoOperations operations;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void saveBook() {
        var genre = new Genre("test genre");
        var author = new Author("testName", "testSurname");
        var book = new Book("test-book-4", "test", author, genre);
        Mono<Book> bookMono = bookRepository.save(book);

        StepVerifier
                .create(bookMono)
                .assertNext(savedBook ->
                        assertThat(savedBook).isNotNull()
                                .hasFieldOrPropertyWithValue("id", book.getId())
                                .hasFieldOrPropertyWithValue("title", book.getTitle())
                                .hasFieldOrPropertyWithValue("author", book.getAuthor())
                                .hasFieldOrPropertyWithValue("genre", book.getGenre())
                )
                .expectComplete()
                .verify();
    }

    @Test
    void findBookById() {
        Mono<Book> bookMono = bookRepository.findById(guideBook.getId());

        StepVerifier
                .create(bookMono)
                .assertNext(book ->
                        assertThat(book).isNotNull()
                                .hasFieldOrPropertyWithValue("id", guideBook.getId())
                                .hasFieldOrPropertyWithValue("title", guideBook.getTitle())
                                .hasFieldOrPropertyWithValue("author", guideBook.getAuthor())
                                .hasFieldOrPropertyWithValue("genre", guideBook.getGenre())
                )
                .expectComplete()
                .verify();
    }

    @Test
    void deleteById() {
        bookRepository.deleteById(guideBook.getId()).block();
        Mono<Book> bookMono = operations.findById(guideBook.getId(), Book.class);

        StepVerifier
                .create(bookMono)
                .verifyComplete();
    }

    @DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
    @Test
    void findAllBooks() {
        Flux<Book> booksFlux = bookRepository.findAll();

        StepVerifier
                .create(booksFlux)
                .expectNext(guideBook, conceptsBook, sqlCodingBook)
                .expectComplete()
                .verify();
    }
}
