package com.mycompany.hw_l20_spring_webflux.controllers;

import com.mycompany.hw_l20_spring_webflux.dto.BookDto;
import com.mycompany.hw_l20_spring_webflux.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookDbService dbService;

    @GetMapping("/api/book")
    public List<BookDto> getAllBooks() {
        return dbService.getAllBooks()
                .stream()
                .map(BookDto::toDto)
                .collect(toList());
    }

    @PostMapping(value = "/api/book")
    public BookDto addBook(@RequestBody BookDto bookDto) {
        var book = dbService.save(BookDto.toDomainObject(bookDto));
        return BookDto.toDto(book);
    }

    @PatchMapping("/api/book/{id}")
    public ResponseEntity<String> updateTitle(@RequestBody BookDto bookDto) {
        dbService.updateTitle(bookDto.getId(), bookDto.getTitle());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        dbService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
