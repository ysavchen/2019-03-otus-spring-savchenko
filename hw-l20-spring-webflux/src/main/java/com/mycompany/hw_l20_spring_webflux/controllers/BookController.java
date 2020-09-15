package com.mycompany.hw_l20_spring_webflux.controllers;

import com.mycompany.hw_l20_spring_webflux.dto.BookDto;
import com.mycompany.hw_l20_spring_webflux.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class BookController {

    private static final String BOOK_LIST_FORM = "/html/books/bookList.html";
    private static final String ADD_BOOK_FORM = "/html/books/addBook.html";
    private static final String VIEW_BOOK_FORM = "/html/books/viewBook";

    private final BookRepository bookRepository;

    @GetMapping("/book/new")
    public Mono<String> showAddForm() {
        return Mono.just(ADD_BOOK_FORM);
    }

    @GetMapping({"/book/", "/"})
    public Mono<String> getAllBooks() {
        return Mono.just(BOOK_LIST_FORM);
    }

    @GetMapping("/book/{id}")
    public Mono<String> getBookById(@PathVariable("id") String id, Model model) {
        return bookRepository.findById(id)
                .map(book -> {
                    model.addAttribute("book", BookDto.toDto(book));
                    return VIEW_BOOK_FORM;
                });
    }
}
