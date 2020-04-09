package com.mycompany.hw_l13_spring_data_nosql.shell;

import com.mycompany.hw_l13_spring_data_nosql.domain.Author;
import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import com.mycompany.hw_l13_spring_data_nosql.domain.Genre;
import com.mycompany.hw_l13_spring_data_nosql.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.function.Function;

import static java.util.stream.Collectors.joining;
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
            book.setAuthor(new Author(authorSurname, authorSurname));
        }
        if (genre != null) {
            book.setGenre(new Genre(genre));
        }

        return "Added book with id = " + dbService.save(book);
    }

    @ShellMethod(value = "Find book by id", key = {"fbi", "find-book-by-id"})
    public String findBookById(String id) {
        var optBook = dbService.getById(id);
        if (optBook.isEmpty()) {
            return "Book with id = " + id + " is not found";
        }

        var book = optBook.get();
        String author = "not defined";
        String genre = "not defined";

        if (book.getAuthor() != null) {
            author = book.getAuthor().getName() + " " + book.getAuthor().getSurname();
        }
        if (book.getGenre() != null) {
            genre = book.getGenre().getName();
        }

        return "Title: " + book.getTitle() + "\n" +
                "Author: " + author + "\n" +
                "Genre: " + genre;
    }

    @ShellMethod(value = "Find books by author id", key = {"fbai", "find-books-by-author-id"})
    public String findBooksByAuthorId(String id) {
        var books = dbService.getBooksByAuthorId(id);
        if (books.isEmpty()) {
            return "No books with authorId = " + id + " found";
        }

        Function<Book, String> bookFunc = book -> {
            String genre = "not defined";

            if (book.getGenre() != null) {
                genre = book.getGenre().getName();
            }
            return book.getTitle() + ", " + genre;
        };
        var booksStr = books.stream()
                .map(bookFunc)
                .collect(joining("\n"));

        var author = books.get(0).getAuthor();
        return "Books with author (id = " + author.getId() + ", " +
                author.getName() + " " + author.getSurname() + "):" +
                "\n" + booksStr;
    }

    @ShellMethod(value = "Find all books", key = {"fab", "find-all-books"})
    public String findAllBooks() {
        var books = dbService.getAllBooks();
        if (books.isEmpty()) {
            return "No books found";
        }

        Function<Book, String> bookFunc = book -> {
            String author = "not defined";
            String genre = "not defined";

            if (book.getAuthor() != null) {
                author = book.getAuthor().getName() + " " + book.getAuthor().getSurname();
            }
            if (book.getGenre() != null) {
                genre = book.getGenre().getName();
            }
            return "Title: " + book.getTitle() + "\n" +
                    "Author: " + author + "\n" +
                    "Genre: " + genre;
        };

        return books.stream()
                .map(bookFunc)
                .collect(joining("\n\n"));
    }

    @ShellMethod(value = "Update title for a book", key = {"utb", "update-title-for-book"})
    public String updateTitle(String id, String newTitle) {
        dbService.updateTitle(id, newTitle);
        return "Title is changed for a book with id = " + id;
    }

    @ShellMethod(value = "Delete book by id", key = {"dbi", "delete-book-by-id"})
    public String deleteBookById(String id) {
        dbService.deleteById(id);
        return "Deleted book with id = " + id;
    }
}
