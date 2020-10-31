package com.mycompany.hw_l34_srping_cloud.controllers;

import com.mycompany.hw_l34_srping_cloud.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;

@Controller
@RequiredArgsConstructor
public class BookController {

    private static final String BOOK_LIST_FORM = "html/books/bookList.html";
    private static final String ADD_BOOK_FORM = "html/books/addBook.html";
    private static final String VIEW_BOOK_FORM = "html/books/viewBook";

    private final BookService bookService;

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
        return bookService.getById(id).map(
                bookDto -> {
                    model.addAttribute("book", bookDto);
                    return VIEW_BOOK_FORM;
                }).orElseThrow(() -> new EntityNotFoundException("Book with id = " + id + " is not found"));
    }
}
