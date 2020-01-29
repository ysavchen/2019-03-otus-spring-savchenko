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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(BookDaoJdbc.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookDaoJdbcTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author author = new Author(1, "Philip", "Pratt");
    private final Book book =
            Book.builder()
                    .id(1).title("A Guide to SQL").author(author).genre(genre)
                    .build();

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    void insertBook() {
        var book = Book.builder().title("test").build();
        long id = bookDaoJdbc.insert(book);
        assertEquals(3, id, "Invalid id for an inserted Book");
    }

    @Test
    void insertBookWithId() {
        var book = Book.builder().id(100).title("A Guide to SQL").build();
        assertEquals(3, bookDaoJdbc.insert(book));
    }

    @Test
    void insertBookWithGenreAndAuthor() {
        var bookWithGenre = Book.builder().title("test").genre(genre).build();
        var bookWithAuthor = Book.builder().title("test").author(author).build();
        var bookWithBoth = Book.builder().title("test").genre(genre).author(author).build();

        assertEquals(3, bookDaoJdbc.insert(bookWithGenre));
        assertEquals(4, bookDaoJdbc.insert(bookWithAuthor));
        assertEquals(5, bookDaoJdbc.insert(bookWithBoth));
    }

    @Test
    void getBookById() {
        assertThat(bookDaoJdbc.getById(book.getId())).get()
                .hasFieldOrPropertyWithValue("id", book.getId())
                .hasFieldOrPropertyWithValue("title", book.getTitle())
                .hasFieldOrPropertyWithValue("author", book.getAuthor())
                .hasFieldOrPropertyWithValue("genre", book.getGenre());
    }

    @Test
    void getBookWithGenreAndAuthor() {

    }

    @Test
    void updateBookTitle() {

    }

    @Test
    void deleteById() {

    }
}
