package com.mycompany.hw_l09_spring_orm_jpa;

import com.mycompany.hw_l09_spring_orm_jpa.dao.BookRepositoryImpl;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Author;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(BookRepositoryImpl.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookRepositoryImplTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author author = new Author(1, "Philip", "Pratt");
    private final Book book = new Book(1, "A Guide to SQL").author(author).genre(genre);
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private BookRepositoryImpl bookDaoJdbc;

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
    void getBookByNonExistingId() {
        assertThat(bookDaoJdbc.getById(NON_EXISTING_ID)).isEmpty();
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
        boolean isUpdated = bookDaoJdbc.update(book.title("newTitle"));
        assertTrue(isUpdated, "Book is not updated by id = " + book.id());
        assertThat(bookDaoJdbc.getById(book.id())).get()
                .hasFieldOrPropertyWithValue("title", book.title());
    }

    @Test
    void updateBookWithNonExistingId() {
        boolean isUpdated = bookDaoJdbc.update(book.id(NON_EXISTING_ID).title("newTitle"));
        assertFalse(isUpdated, "Book with non-existing id is updated");
    }

    @Test
    void deleteById() {
        boolean isDeleted = bookDaoJdbc.deleteById(book.id());
        assertTrue(isDeleted, "Book is not deleted by id = " + book.id());
        assertThat(bookDaoJdbc.getById(book.id())).isEmpty();
    }

    @Test
    void deleteByNonExistingId() {
        boolean isDeleted = bookDaoJdbc.deleteById(NON_EXISTING_ID);
        assertFalse(isDeleted, "Book with non-existing id is deleted");
    }
}
