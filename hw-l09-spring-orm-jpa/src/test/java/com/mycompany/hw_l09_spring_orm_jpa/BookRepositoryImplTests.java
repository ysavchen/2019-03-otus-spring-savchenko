package com.mycompany.hw_l09_spring_orm_jpa;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Author;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Genre;
import com.mycompany.hw_l09_spring_orm_jpa.repositories.BookRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
    private final Author authorTwo = new Author(2, "Michael", "Learn");
    private final Book bookOne = new Book(1, "A Guide to SQL").author(author).genre(genre);
    private final Book bookTwo = new Book(2, "Concepts of Database Management").author(author).genre(genre);
    private final Book bookThree = new Book(3, "SQL Programming and Coding").author(authorTwo).genre(genre);
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private BookRepositoryImpl bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void insertBook() {
        var book = new Book("test");
        long id = bookRepository.insert(book);
        assertEquals(4, id, "Invalid id for an inserted Book");
    }

    @Test
    void insertBookWithGenreAndAuthor() {
        var testGenre = new Genre("test genre");
        var testAuthor = new Author("testName", "testSurname");

        var bookWithGenre = new Book("test").genre(testGenre);
        var bookWithAuthor = new Book("test").author(testAuthor);
        var bookWithBoth = new Book("test").author(testAuthor).genre(testGenre);

        assertEquals(4, bookRepository.insert(bookWithGenre));
        assertEquals(5, bookRepository.insert(bookWithAuthor));
        assertEquals(6, bookRepository.insert(bookWithBoth));
    }

    @Test
    void getBookById() {
        assertThat(bookRepository.getById(bookOne.id())).get()
                .hasFieldOrPropertyWithValue("id", bookOne.id())
                .hasFieldOrPropertyWithValue("title", bookOne.title())
                .hasFieldOrPropertyWithValue("author", bookOne.author())
                .hasFieldOrPropertyWithValue("genre", bookOne.genre());
    }

    @Test
    void getBookByNonExistingId() {
        assertThat(bookRepository.getById(NON_EXISTING_ID)).isEmpty();
    }

    @Test
    void getBookWithGenreAndAuthor() {
        var testGenre = new Genre("test genre");
        var testAuthor = new Author("testName", "testSurname");

        var bookWithGenre = new Book("test").genre(testGenre);
        var bookWithAuthor = new Book("test").author(testAuthor);
        var bookWithBoth = new Book("test").author(testAuthor).genre(testGenre);

        List.of(bookWithGenre, bookWithAuthor, bookWithBoth)
                .forEach(testBook -> {
                    long id = bookRepository.insert(testBook);
                    assertThat(bookRepository.getById(id)).get()
                            .hasFieldOrPropertyWithValue("id", id)
                            .hasFieldOrPropertyWithValue("title", testBook.title())
                            .hasFieldOrPropertyWithValue("author", testBook.author())
                            .hasFieldOrPropertyWithValue("genre", testBook.genre());
                });
    }

    @Test
    void updateBookTitle() {
        boolean isUpdated = bookRepository.update(bookOne.title("newTitle"));
        assertTrue(isUpdated, "Book is not updated by id = " + bookOne.id());
        assertThat(bookRepository.getById(bookOne.id())).get()
                .hasFieldOrPropertyWithValue("title", bookOne.title());
    }

    @Test
    void updateBookWithNonExistingId() {
        boolean isUpdated = bookRepository.update(bookOne.id(NON_EXISTING_ID).title("newTitle"));
        assertFalse(isUpdated, "Book with non-existing id is updated");
    }

    @Test
    void deleteById() {
        boolean isDeleted = bookRepository.deleteById(bookOne.id());
        assertTrue(isDeleted, "Book is not deleted by id = " + bookOne.id());
        assertThat(bookRepository.getById(bookOne.id())).isEmpty();
    }

    @Test
    void deleteByNonExistingId() {
        boolean isDeleted = bookRepository.deleteById(NON_EXISTING_ID);
        assertFalse(isDeleted, "Book with non-existing id is deleted");
    }

    @Test
    void getBooksByAuthorId() {
        var books = bookRepository.getBooksByAuthorId(author.id());
        assertThat(books).containsExactlyInAnyOrder(bookOne, bookTwo);
    }

    @Test
    void getBooksByNonExistingAuthorId() {
        var books = bookRepository.getBooksByAuthorId(NON_EXISTING_ID);
        assertThat(books).isEmpty();
    }

    @Test
    void getAllBooks() {
        var books = bookRepository.getAllBooks();
        assertThat(books).containsExactlyInAnyOrder(bookOne, bookTwo, bookThree);
    }
}
