package com.mycompany.hw_l20_spring_webflux.controllers;

import com.mycompany.hw_l20_spring_webflux.dto.BookDto;
import com.mycompany.hw_l20_spring_webflux.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookRepository bookRepository;

    @GetMapping("/api/book")
    public Flux<BookDto> getAllBooks() {
        return bookRepository.findAll().map(BookDto::toDto);
    }

    @PostMapping(value = "/api/book",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDto> addBook(@RequestBody BookDto bookDto) {
        return bookRepository.save(BookDto.toDomainObject(bookDto)).map(BookDto::toDto);
    }

    @PatchMapping(value = "/api/book/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> updateTitle(@RequestBody BookDto bookDto) {
        return bookRepository.updateTitle(bookDto.getId(), bookDto.getTitle());
    }

    @DeleteMapping("/api/book/{id}")
    public Mono<Void> deleteBook(@PathVariable("id") String id) {
        return bookRepository.deleteById(id);
    }
}
