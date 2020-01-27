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

    private final BookDbService bookDbService;

    @ShellMethod(value = "Add book", key = {"ab", "add-book"})
    public String addBook(String title, String authorName,
                          String authorSurname, String genre) {

        var author = new Author(authorName, authorSurname);
        var book = new Book(title, author, new Genre(genre));

        return "Added book with id = " + bookDbService.insert(book);
    }

    @ShellMethod(value = "Find book by title", key = {"fbt", "find-book-by-title"})
    public String findBookByTitle(String title) {
        return "";
    }

    @ShellMethod(value = "Update title for a book", key = {"utb", "update-title-for-book"})
    public String updateTitle(long id, String newTitle) {


        return "Title is changed for a book with id = " + id;
    }

    @ShellMethod(value = "Update author for a book", key = {"uab", "update-author-for-book"})
    public String updateAuthor(long id, String newAuthor) {

        return "Author is changed for a book with id = " + id;
    }

    @ShellMethod(value = "Update genre for a book", key = {"ugb", "update-genre-for-book"})
    public String updateGenre(long id, String newGenre) {


        return "Genre is changed for a book with id = " + id;
    }

    @ShellMethod(value = "Delete book by id", key = {"dbi", "delete-book-by-id"})
    public String deleteBookByTitle(long id) {


        return "Deleted book with id = " + id;
    }

    @ShellMethod(value = "Find all books", key = {"fab", "find-all-books"})
    public String getAllBooks() {
        return "";
    }
}
