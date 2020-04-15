package com.mycompany.hw_l17_spring_mvc_modern_apps.controllers;

import com.mycompany.hw_l17_spring_mvc_modern_apps.domain.Book;
import com.mycompany.hw_l17_spring_mvc_modern_apps.dto.BookDto;
import com.mycompany.hw_l17_spring_mvc_modern_apps.exceptions.EntityNotFoundException;
import com.mycompany.hw_l17_spring_mvc_modern_apps.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BookController {

    private static final String BOOK_LIST_FORM = "/html/books/bookList.html";
    private static final String VIEW_BOOK_FORM = "/html/books/viewBook";
    private static final String ADD_BOOK_FORM = "/html/books/addBook.html";

    private static final String BOOK_LIST_REDIRECT = "redirect:/book/all";
    private static final String VIEW_BOOK_REDIRECT = "redirect:/book/";

    private final BookDbService dbService;

    @GetMapping("/book/new")
    public String showAddForm() {
        return ADD_BOOK_FORM;
    }

    @GetMapping({"/book/all", "/"})
    public String getAllBooks() {
        return BOOK_LIST_FORM;
    }

    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable("id") long id,
                              Model model) {
        return dbService.getById(id).map(
                book -> {
                    model.addAttribute("book", BookDto.toDto(book));
                    return VIEW_BOOK_FORM;
                }).orElseThrow(() -> new EntityNotFoundException("Book with id = " + id + " is not found"));
    }

    //todo: change to jQuery via REST
    @PostMapping("/book/update/{id}")
    public String updateTitle(@PathVariable("id") long id,
                              BookDto bookDto,
                              RedirectAttributes attributes) {
        dbService.updateTitle(id, bookDto.getTitle());
        Book book = dbService.getById(id).orElse(null);

        if (book != null) {
            return VIEW_BOOK_REDIRECT + book.getId();
        } else {
            var message = "Book with id = " + id + " is not found";
            attributes.addFlashAttribute("message", message);
            return BOOK_LIST_REDIRECT;
        }
    }

    //todo: change to jQuery via REST
    @PostMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, RedirectAttributes attributes) {
        dbService.deleteById(id);
        attributes.addFlashAttribute("message", "Book with id = " + id + " is deleted");
        return BOOK_LIST_REDIRECT;
    }


}
