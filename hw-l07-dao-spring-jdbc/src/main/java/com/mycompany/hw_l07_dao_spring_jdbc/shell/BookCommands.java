package com.mycompany.hw_l07_dao_spring_jdbc.shell;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Genre;
import com.mycompany.hw_l07_dao_spring_jdbc.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookDbService dbService;

    @ShellMethod(value = "Add book", key = {"ab", "add-book"})
    public String addBook(String title, String authorName,
                          String authorSurname, String genre) {

        var book = Book.builder()
                .title(title)
                .author(new Author(authorName, authorSurname))
                .genre(new Genre(genre))
                .build();
        return "Added book with id = " + dbService.insert(book);
    }

    @ShellMethod(value = "Find book by id", key = {"fbi", "find-book-by-id"})
    public String findBookById(long id) {
        var optBook = dbService.getById(id);

        if (optBook.isEmpty()) {
            return "Book with id = " + id + " is not found";
        }

        var book = optBook.get();
        return "Title: " + book.getTitle() + "\n" +
                "Author: " + book.getAuthor().getName() + " " + book.getAuthor().getSurname() + "\n" +
                "Genre: " + book.getGenre().getName();
    }

    @ShellMethod(value = "Update title for a book", key = {"utb", "update-title-for-book"})
    public String updateTitle(long id, String newTitle) {
        var book = Book.builder()
                .id(id).title(newTitle)
                .build();

        dbService.update(book);
        return "Title is changed for a book with id = " + id;
    }

    @ShellMethod(value = "Delete book by id", key = {"dbi", "delete-book-by-id"})
    public String deleteBookById(long id) {
        dbService.deleteById(id);
        return "Deleted book with id = " + id;
    }
}
