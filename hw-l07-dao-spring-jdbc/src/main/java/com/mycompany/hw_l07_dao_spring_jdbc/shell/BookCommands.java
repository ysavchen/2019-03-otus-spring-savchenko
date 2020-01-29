package com.mycompany.hw_l07_dao_spring_jdbc.shell;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Genre;
import com.mycompany.hw_l07_dao_spring_jdbc.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import static org.springframework.shell.standard.ShellOption.NULL;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookDbService dbService;

    @ShellMethod(value = "Add book", key = {"ab", "add-book"})
    public String addBook(String title,
                          @ShellOption(defaultValue = NULL) String authorName,
                          @ShellOption(defaultValue = NULL) String authorSurname,
                          @ShellOption(defaultValue = NULL) String genre) {

        var book = new Book(title);
        if (authorName != null && authorSurname != null) {
            book.author(new Author(authorSurname, authorSurname));
        }
        if (genre != null) {
            book.genre(new Genre(genre));
        }

        return "Added book with id = " + dbService.insert(book);
    }

    @ShellMethod(value = "Find book by id", key = {"fbi", "find-book-by-id"})
    public String findBookById(long id) {
        var optBook = dbService.getById(id);

        if (optBook.isEmpty()) {
            return "Book with id = " + id + " is not found";
        }

        var book = optBook.get();
        return "Title: " + book.title() + "\n" +
                "Author: " + book.author().getName() + " " + book.author().getSurname() + "\n" +
                "Genre: " + book.genre().getName();
    }

    @ShellMethod(value = "Update title for a book", key = {"utb", "update-title-for-book"})
    public String updateTitle(long id, String newTitle) {
        var book = new Book(id, newTitle);
        dbService.update(book);
        return "Title is changed for a book with id = " + id;
    }

    @ShellMethod(value = "Delete book by id", key = {"dbi", "delete-book-by-id"})
    public String deleteBookById(long id) {
        dbService.deleteById(id);
        return "Deleted book with id = " + id;
    }
}
