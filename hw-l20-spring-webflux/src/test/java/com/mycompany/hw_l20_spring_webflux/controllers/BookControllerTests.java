package com.mycompany.hw_l20_spring_webflux.controllers;

import com.mycompany.hw_l20_spring_webflux.domain.Author;
import com.mycompany.hw_l20_spring_webflux.domain.Book;
import com.mycompany.hw_l20_spring_webflux.domain.Genre;
import com.mycompany.hw_l20_spring_webflux.dto.BookDto;
import com.mycompany.hw_l20_spring_webflux.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebFluxTest(BookController.class)
public class BookControllerTests {

    private final Genre genre = new Genre("test-genre-1", "Computers & Technology");
    private final Author prattAuthor = new Author("test-author-1", "Philip", "Pratt");
    private final Author learnAuthor = new Author("test-author-1", "Michael", "Learn");
    private final Book guideBook = new Book("test-book-1", "A Guide to SQL", prattAuthor, genre);
    private final Book conceptsBook = new Book("test-book-2", "Concepts of Database Management", prattAuthor, genre);
    private final Book sqlCodingBook = new Book("test-book-3", "SQL Programming and Coding", learnAuthor, genre);
    private final List<Book> books = List.of(guideBook, conceptsBook, sqlCodingBook);

    private final BookDto guideBookDto = BookDto.toDto(guideBook);

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void getBookById() {
        when(bookRepository.findById(anyString())).thenReturn(Mono.just(guideBook));

        webClient.get().uri("/book/{id}", guideBookDto.getId())
                .accept(MediaType.TEXT_HTML)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.TEXT_HTML);
    }

    @ParameterizedTest
    @ValueSource(strings = {"/book/", "/"})
    public void getAllBooks(String uri) {
        when(bookRepository.findAll()).thenReturn(Flux.fromIterable(books));

        webClient.get().uri(uri)
                .accept(MediaType.TEXT_HTML)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.TEXT_HTML);
    }

    @Test
    public void showAddForm() {
        webClient.get().uri("/book/new")
                .accept(MediaType.TEXT_HTML)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.TEXT_HTML);
    }
}
