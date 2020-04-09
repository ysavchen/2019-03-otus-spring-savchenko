package com.mycompany.hw_l16_spring_mvc_view.controllers;

import com.mycompany.hw_l16_spring_mvc_view.domain.Book;
import com.mycompany.hw_l16_spring_mvc_view.dto.AuthorDto;
import com.mycompany.hw_l16_spring_mvc_view.dto.BookDto;
import com.mycompany.hw_l16_spring_mvc_view.dto.GenreDto;
import com.mycompany.hw_l16_spring_mvc_view.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static java.util.stream.Collectors.toList;

@Controller
@RequiredArgsConstructor
public class BookController {

    private static final String BOOK_LIST_FORM = "books/bookList";
    private static final String VIEW_BOOK_FORM = "books/viewBook";
    private static final String ADD_BOOK_FORM = "books/addBook";

    private static final String BOOK_LIST_REDIRECT = "redirect:/book/all";
    private static final String ADD_BOOK_REDIRECT = "redirect:/book/new";
    private static final String VIEW_BOOK_REDIRECT = "redirect:/book/";

    private final BookDbService dbService;

    @GetMapping("/book/new")
    public String showAddForm(Model model) {
        var book = new BookDto()
                .setAuthor(new AuthorDto())
                .setGenre(new GenreDto());
        model.addAttribute("book", book);
        return ADD_BOOK_FORM;
    }

    @PostMapping(value = "/book/new")
    public String addBook(BookDto bookDto, Model model) {
        Book book = dbService.save(BookDto.toDomainObject(bookDto));
        if (book != null) {
            model.addAttribute("message", "Book is successfully saved!");
            model.addAttribute("book", BookDto.toDto(book));
            return VIEW_BOOK_REDIRECT + book.getId();
        } else {
            model.addAttribute("message", "Book is not saved!");
            model.addAttribute("book", bookDto);
            return ADD_BOOK_REDIRECT;
        }
    }

    @PostMapping("/book/update/{id}")
    public String updateTitle(@PathVariable("id") long id,
                              BookDto bookDto,
                              Model model) {
        dbService.updateTitle(id, bookDto.getTitle());
        Book book = dbService.getById(id).orElse(null);

        if (book != null) {
            model.addAttribute("message", "Title is updated!");
            model.addAttribute("book", BookDto.toDto(book));
            return VIEW_BOOK_REDIRECT + book.getId();
        } else {
            var message = "Book with id = " + id + " is not found";
            model.addAttribute("message", message);
            return BOOK_LIST_REDIRECT;
        }
    }

    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable("id") long id, Model model) {
        dbService.getById(id).ifPresentOrElse(
                book -> {
                    model.addAttribute("message", "");
                    model.addAttribute("book", BookDto.toDto(book));
                },
                () -> {
                    var message = "Book with id = " + id + " is not found";
                    model.addAttribute("message", message);
                    model.addAttribute("book", null);
                }
        );

        return VIEW_BOOK_FORM;
    }

    @PostMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model) {
        dbService.deleteById(id);
        var books = dbService.getAllBooks()
                .stream()
                .map(BookDto::toDto)
                .collect(toList());

        model.addAttribute("message", "Book with id = " + id + " is deleted");
        model.addAttribute("books", books);
        return BOOK_LIST_REDIRECT;
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
