package com.mycompany.hw_l07_dao_spring_jdbc.shell;

import com.mycompany.hw_l07_dao_spring_jdbc.dao.BookDao;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookDao bookDao;

    @ShellMethod(value = "Add book", key = {"ab", "add-book"})
    public String addBook(String title, String authorName,
                          String authorSurname, String genre) {

        var author = new Author(authorName, authorSurname);
        var book = new Book(title, author, new Genre(genre));

        return "Added book with id = " + bookDao.insert(book);
    }

    @ShellMethod(value = "Find book by id", key = {"fbi", "find-book-by-id"})
    public String findBookById(String title) {
        return "";
    }

    @ShellMethod(value = "Find book by title", key = {"fbt", "find-book-by-title"})
    public String findBookByTitle(String title) {
        return "";
    }

    @ShellMethod(value = "Delete book by id", key = {"dbi", "delete-book-by-id"})
    public String deleteBookById(long id) {
        bookDao.deleteById(id);
        return "Deleted book with id = " + id;
    }

    @ShellMethod(value = "Find all books", key = {"fab", "find-all-books"})
    public String getAllBooks() {
        return "";
    }
}
