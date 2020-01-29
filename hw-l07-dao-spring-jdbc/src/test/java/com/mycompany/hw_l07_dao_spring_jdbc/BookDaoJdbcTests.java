package com.mycompany.hw_l07_dao_spring_jdbc;

import com.mycompany.hw_l07_dao_spring_jdbc.dao.BookDaoJdbc;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(BookDaoJdbc.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookDaoJdbcTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author author = new Author(1, "Philip", "Pratt");
    private final Book book = new Book(1, "A Guide to SQL").author(author).genre(genre);

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    void insertBook() {
        var book = new Book("test");
        long id = bookDaoJdbc.insert(book);
        assertEquals(3, id, "Invalid id for an inserted Book");
    }

    @Test
    void insertBookWithId() {
        var book = new Book(100, "test");
        assertEquals(3, bookDaoJdbc.insert(book));
    }

    @Test
    void insertBookWithGenreAndAuthor() {
        var bookWithGenre = new Book("test").genre(genre);
        var bookWithAuthor = new Book("test").author(author);
        var bookWithBoth = new Book("test").author(author).genre(genre);

        assertEquals(3, bookDaoJdbc.insert(bookWithGenre));
        assertEquals(4, bookDaoJdbc.insert(bookWithAuthor));
        assertEquals(5, bookDaoJdbc.insert(bookWithBoth));
    }

    @Test
    void getBookById() {
        assertThat(bookDaoJdbc.getById(book.id())).get()
                .hasFieldOrPropertyWithValue("id", book.id())
                .hasFieldOrPropertyWithValue("title", book.title())
                .hasFieldOrPropertyWithValue("author", book.author())
                .hasFieldOrPropertyWithValue("genre", book.genre());
    }

    @Test
    void getBookWithGenreAndAuthor() {
        var bookWithGenre = new Book("test").genre(genre);
        var bookWithAuthor = new Book("test").author(author);
        var bookWithBoth = new Book("test").author(author).genre(genre);

        List.of(bookWithGenre, bookWithAuthor, bookWithBoth)
                .forEach(testBook -> {
                    long id = bookDaoJdbc.insert(testBook);
                    assertThat(bookDaoJdbc.getById(id)).get()
                            .hasFieldOrPropertyWithValue("id", id)
                            .hasFieldOrPropertyWithValue("title", testBook.title())
                            .hasFieldOrPropertyWithValue("author", testBook.author())
                            .hasFieldOrPropertyWithValue("genre", testBook.genre());
                });
    }

    @Test
    void updateBookTitle() {

    }

    @Test
    void deleteById() {

    }
}
