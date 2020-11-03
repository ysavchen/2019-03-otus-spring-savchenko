package com.mycompany.hw_l34_srping_cloud.controllers;

import com.mycompany.hw_l34_srping_cloud.dto.BookDto;
import com.mycompany.hw_l34_srping_cloud.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping("/api/book")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping(value = "/api/book")
    public BookDto addBook(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @PatchMapping("/api/book/{id}")
    public ResponseEntity<String> updateTitle(@RequestBody BookDto bookDto) {
        bookService.updateTitle(bookDto.getId(), bookDto.getTitle());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
