package com.mycompany.hw_l16_spring_mvc_view.controllers;

import com.mycompany.hw_l16_spring_mvc_view.dto.BookDto;
import com.mycompany.hw_l16_spring_mvc_view.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequiredArgsConstructor
public class BookController {

    private static final String BOOK_LIST_FORM = "books/bookList";

    private final BookDbService dbService;

    @PostMapping("/book")
    public String addBook(@RequestBody BookDto bookDto, Model model) {
        return "";
    }

    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable("id") long id, Model model) {
        return "";
    }

    @GetMapping("/book/all")
    public String getAllBooks(Model model) {
        List<BookDto> books = dbService.getAllBooks()
                .stream()
                .map(BookDto::toDto)
                .collect(toList());

        model.addAttribute("books", books);
        return BOOK_LIST_FORM;
    }
}
