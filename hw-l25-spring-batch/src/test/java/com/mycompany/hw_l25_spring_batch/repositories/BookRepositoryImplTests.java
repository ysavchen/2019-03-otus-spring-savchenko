package com.mycompany.hw_l25_spring_batch.repositories;

import com.mycompany.hw_l25_spring_batch.AbstractRepositoryTest;
import com.mycompany.hw_l25_spring_batch.domain.Author;
import com.mycompany.hw_l25_spring_batch.domain.Book;
import com.mycompany.hw_l25_spring_batch.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BookRepositoryImplTests extends AbstractRepositoryTest {

    private final Genre genre = new Genre("Computers & Technology").setId("1");
    private final Author prattAuthor = new Author("Philip", "Pratt").setId("1");
    private final Author learnAuthor = new Author("Michael", "Learn").setId("2");

    private final Book guideBook = new Book("A Guide to SQL", prattAuthor, genre).setId("1");
    private final Book conceptsBook = new Book("Concepts of Database Management", prattAuthor, genre).setId("2");
    private final Book sqlCodingBook = new Book("SQL Programming and Coding", learnAuthor, genre).setId("3");
    private static final String NON_EXISTING_ID = "50";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoOperations operations;

    @Test
    void saveBook() {
        var book = new Book("test");
        String id = bookRepository.save(book).getId();
        assertFalse(id.isEmpty(), "Invalid id for an saved Book");
    }

    @Test
    void saveBookWithGenre() {
        var genre = new Genre("test genre");
        var bookWithGenre = new Book("test").setGenre(genre);
        String id = bookRepository.save(bookWithGenre).getId();
        assertFalse(id.isEmpty(),
                "Book with genre is not saved");
    }

    @Test
    void saveBookWithAuthor() {
        var testAuthor = new Author("testName", "testSurname");
        var bookWithAuthor = new Book("test");
        operations.save(testAuthor);
        bookWithAuthor.setAuthor(testAuthor);

        String id = bookRepository.save(bookWithAuthor).getId();
        assertFalse(id.isEmpty(),
                "Book with author is not saved");
    }

    @Test
    void saveBookWithGenreAndAuthor() {
        var testGenre = new Genre("test genre");
        var testAuthor = new Author("testName", "testSurname");
        var bookWithBoth = new Book("test").setGenre(testGenre);
        operations.save(testAuthor);
        bookWithBoth.setAuthor(testAuthor);

        String id = bookRepository.save(bookWithBoth).getId();
        assertFalse(id.isEmpty(),
                "Book with genre and author is not saved");
    }

    @Test
    void findBookById() {
        assertThat(bookRepository.findById(guideBook.getId())).get()
                .hasFieldOrPropertyWithValue("id", guideBook.getId())
                .hasFieldOrPropertyWithValue("title", guideBook.getTitle())
                .hasFieldOrPropertyWithValue("author", guideBook.getAuthor())
                .hasFieldOrPropertyWithValue("genre", guideBook.getGenre());
    }

    @Test
    void findBookByNonExistingId() {
        assertThat(bookRepository.findById(NON_EXISTING_ID)).isEmpty();
    }

    @Test
    void findBookWithGenre() {
        var testGenre = new Genre("test genre");
        var book = new Book("test").setGenre(testGenre);
        String id = operations.save(book).getId();

        assertThat(bookRepository.findById(id)).get()
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("title", book.getTitle())
                .hasFieldOrPropertyWithValue("author", null)
                .hasFieldOrPropertyWithValue("genre", testGenre);
    }

    @Test
    void findBookWithAuthor() {
        var testAuthor = new Author("testName", "testSurname");
        var book = new Book("test");
        operations.save(testAuthor);
        book.setAuthor(testAuthor);
        String id = operations.save(book).getId();

        assertThat(bookRepository.findById(id)).get()
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("title", book.getTitle())
                .hasFieldOrPropertyWithValue("author", testAuthor)
                .hasFieldOrPropertyWithValue("genre", null);
    }

    @Test
    void findBookWithGenreAndAuthor() {
        var testGenre = new Genre("test genre");
        var testAuthor = new Author("testName", "testSurname");
        var book = new Book("test").setGenre(testGenre);
        operations.save(testAuthor);
        book.setAuthor(testAuthor);

        String id = operations.save(book).getId();
        assertThat(bookRepository.findById(id)).get()
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("title", book.getTitle())
                .hasFieldOrPropertyWithValue("author", testAuthor)
                .hasFieldOrPropertyWithValue("genre", testGenre);
    }

    @Test
    void deleteById() {
        bookRepository.deleteById(guideBook.getId());
        assertThat(operations.findById(guideBook.getId(), Book.class)).isNull();
    }

    @Test
    void findBooksByAuthorId() {
        var books = bookRepository.findByAuthorId(prattAuthor.getId());
        assertThat(books).containsExactlyInAnyOrder(guideBook, conceptsBook);
    }

    @Test
    void findBooksByNonExistingAuthorId() {
        var books = bookRepository.findByAuthorId(NON_EXISTING_ID);
        assertThat(books).isEmpty();
    }

    @DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
    @Test
    void findAllBooks() {
        var books = bookRepository.findAll();
        assertThat(books).containsExactlyInAnyOrder(guideBook, conceptsBook, sqlCodingBook);
    }
}
