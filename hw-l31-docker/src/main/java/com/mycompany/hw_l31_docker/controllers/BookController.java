package com.mycompany.hw_l31_docker.controllers;

import com.mycompany.hw_l31_docker.dto.BookDto;
import com.mycompany.hw_l31_docker.exceptions.EntityNotFoundException;
import com.mycompany.hw_l31_docker.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BookController {

    private static final String BOOK_LIST_FORM = "html/books/bookList.html";
    private static final String ADD_BOOK_FORM = "html/books/addBook.html";
    private static final String VIEW_BOOK_FORM = "html/books/viewBook";

    private final BookDbService dbService;

    @GetMapping("/book/new")
    public String showAddForm() {
        return ADD_BOOK_FORM;
    }

    @GetMapping({"/book/", "/"})
    public String getAllBooks() {
        return BOOK_LIST_FORM;
    }

    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable("id") long id, Model model) {
        return dbService.getById(id).map(
                book -> {
                    model.addAttribute("book", BookDto.toDto(book));
                    return VIEW_BOOK_FORM;
                }).orElseThrow(() -> new EntityNotFoundException("Book with id = " + id + " is not found"));
    }
}
