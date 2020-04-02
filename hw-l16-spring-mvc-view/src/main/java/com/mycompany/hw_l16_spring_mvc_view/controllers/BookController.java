package com.mycompany.hw_l16_spring_mvc_view.controllers;

import com.mycompany.hw_l16_spring_mvc_view.dto.BookDto;
import com.mycompany.hw_l16_spring_mvc_view.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;

@Controller
@RequiredArgsConstructor
public class BookController {

    private static final String BOOK_LIST_FORM = "books/bookList";
    private static final String VIEW_BOOK_FORM = "books/viewBook";

    private final BookDbService dbService;

    @PostMapping("/book/new")
    public String addBook(@RequestBody BookDto bookDto, Model model) {
        return "";
    }

    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable("id") long id,
                              @RequestParam("editable") boolean isEditable,
                              Model model) {

        String notFoundMsg = "Book with id = " + id + " is not found";
        dbService.getById(id).ifPresentOrElse(
                book -> {
                    model.addAttribute("message", "");
                    model.addAttribute("book", BookDto.toDto(book));
                },
                () -> {
                    model.addAttribute("message", notFoundMsg);
                    model.addAttribute("book", null);
                }
        );

        return VIEW_BOOK_FORM;
    }

    @GetMapping({"/book/all", "/"})
    public String getAllBooks(Model model) {
        var books = dbService.getAllBooks()
                .stream()
                .map(BookDto::toDto)
                .collect(toList());

        model.addAttribute("books", books);
        return BOOK_LIST_FORM;
    }
}
