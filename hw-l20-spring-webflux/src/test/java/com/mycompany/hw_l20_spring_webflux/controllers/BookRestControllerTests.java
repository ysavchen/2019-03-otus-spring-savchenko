package com.mycompany.hw_l20_spring_webflux.controllers;

import com.google.gson.Gson;
import com.mycompany.hw_l20_spring_webflux.domain.Author;
import com.mycompany.hw_l20_spring_webflux.domain.Book;
import com.mycompany.hw_l20_spring_webflux.domain.Genre;
import com.mycompany.hw_l20_spring_webflux.dto.BookDto;
import com.mycompany.hw_l20_spring_webflux.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebFluxTest(BookRestController.class)
public class BookRestControllerTests {

    private final Genre genre = new Genre("test-genre-1", "Computers & Technology");
    private final Author prattAuthor = new Author("test-author-1", "Philip", "Pratt");
    private final Author learnAuthor = new Author("test-author-2", "Michael", "Learn");
    private final Book guideBook = new Book("test-book-1", "A Guide to SQL", prattAuthor, genre);
    private final Book conceptsBook = new Book("test-book-2", "Concepts of Database Management", prattAuthor, genre);
    private final Book sqlCodingBook = new Book("test-book-3", "SQL Programming and Coding", learnAuthor, genre);

    private final BookDto guideBookDto = BookDto.toDto(guideBook);
    private final BookDto conceptsBookDto = BookDto.toDto(conceptsBook);
    private final BookDto sqlCodingBookDto = BookDto.toDto(sqlCodingBook);

    private final List<Book> books = List.of(guideBook, conceptsBook, sqlCodingBook);

    private final Gson gson = new Gson();

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void getAllBooks() {
        when(bookRepository.findAll()).thenReturn(Flux.fromIterable(books));

        webClient.get().uri("/api/book")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(BookDto.class).contains(guideBookDto, conceptsBookDto, sqlCodingBookDto);
    }

    @Test
    public void updateTitle() {
        when(bookRepository.updateTitle(anyString(), anyString())).thenReturn(Mono.empty());

        var title = "test title";
        webClient.patch().uri("/api/book/{id}", guideBookDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(guideBookDto.setTitle(title)), BookDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void deleteBook() {
        when(bookRepository.deleteById(anyString())).thenReturn(Mono.empty());

        webClient.delete().uri("/api/book/{id}", guideBookDto.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void addBook() {
        var testBook = new Book("new-test-book-id", "New Test Book", prattAuthor, genre);
        var testBookDto = BookDto.toDto(testBook);
        when(bookRepository.save(any())).thenReturn(Mono.just(testBook));

        webClient.post().uri("/api/book")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(testBook), BookDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json(gson.toJson(testBookDto));
    }
}
