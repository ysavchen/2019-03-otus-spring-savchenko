package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Author;
import com.mycompany.hw_l11_spring_data_jpa.domain.Book;
import com.mycompany.hw_l11_spring_data_jpa.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookRepositoryImplTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author pratt = new Author(1, "Philip", "Pratt");
    private final Author learn = new Author(2, "Michael", "Learn");
    private final Book guide = new Book(1, "A Guide to SQL", pratt, genre);
    private final Book concepts = new Book(2, "Concepts of Database Management", pratt, genre);
    private final Book sqlCoding = new Book(3, "SQL Programming and Coding", learn, genre);
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void insertBook() {
        var book = new Book("test");
        long id = bookRepository.save(book).getId();
        assertEquals(4, id, "Invalid id for an inserted Book");
    }

    @Test
    void insertBookWithGenreAndAuthor() {
        var testGenre = new Genre("test genre");
        var testAuthor = new Author("testName", "testSurname");

        var bookWithGenre = new Book("test").setGenre(testGenre);
        var bookWithAuthor = new Book("test").setAuthor(testAuthor);
        var bookWithBoth = new Book("test").setAuthor(testAuthor).setGenre(testGenre);

        assertEquals(4, bookRepository.save(bookWithGenre).getId());
        assertEquals(5, bookRepository.save(bookWithAuthor).getId());
        assertEquals(6, bookRepository.save(bookWithBoth).getId());
    }

    @Test
    void getBookById() {
        assertThat(bookRepository.findById(guide.getId())).get()
                .hasFieldOrPropertyWithValue("id", guide.getId())
                .hasFieldOrPropertyWithValue("title", guide.getTitle())
                .hasFieldOrPropertyWithValue("author", guide.getAuthor())
                .hasFieldOrPropertyWithValue("genre", guide.getGenre());
    }

    @Test
    void getBookByNonExistingId() {
        assertThat(bookRepository.findById(NON_EXISTING_ID)).isEmpty();
    }

    @Test
    void getBookWithGenreAndAuthor() {
        var testGenre = new Genre("test genre");
        var testAuthor = new Author("testName", "testSurname");

        var bookWithGenre = new Book("test").setGenre(testGenre);
        var bookWithAuthor = new Book("test").setAuthor(testAuthor);
        var bookWithBoth = new Book("test").setAuthor(testAuthor).setGenre(testGenre);

        List.of(bookWithGenre, bookWithAuthor, bookWithBoth)
                .forEach(testBook -> {
                    long id = bookRepository.save(testBook).getId();
                    assertThat(bookRepository.findById(id)).get()
                            .hasFieldOrPropertyWithValue("id", id)
                            .hasFieldOrPropertyWithValue("title", testBook.getTitle())
                            .hasFieldOrPropertyWithValue("author", testBook.getAuthor())
                            .hasFieldOrPropertyWithValue("genre", testBook.getGenre());
                });
    }

    @Test
    void updateBookTitle() {
        bookRepository.updateTitle(guide.getId(), "newTitle");
        //assertTrue(isUpdated, "Book is not updated by id = " + guide.id());
        assertThat(bookRepository.findById(guide.getId())).get()
                .hasFieldOrPropertyWithValue("title", guide.getTitle());
    }

    @Test
    void updateBookWithNonExistingId() {
        bookRepository.updateTitle(NON_EXISTING_ID, "newTitle");
        //assertFalse(isUpdated, "Book with non-existing id is updated");
    }

    @Test
    void deleteById() {
        bookRepository.deleteById(guide.getId());
        //assertTrue(isDeleted, "Book is not deleted by id = " + guide.id());
        assertThat(bookRepository.findById(guide.getId())).isEmpty();
    }

    @Test
    void deleteByNonExistingId() {
        bookRepository.deleteById(NON_EXISTING_ID);
        //assertFalse(isDeleted, "Book with non-existing id is deleted");
    }

    @Test
    void getBooksByAuthorId() {
        var books = bookRepository.findByAuthorId(pratt.getId());
        assertThat(books).containsExactlyInAnyOrder(guide, concepts);
    }

    @Test
    void getBooksByNonExistingAuthorId() {
        var books = bookRepository.findByAuthorId(NON_EXISTING_ID);
        assertThat(books).isEmpty();
    }

    @Test
    void getAllBooks() {
        var books = bookRepository.findAll();
        assertThat(books).containsExactlyInAnyOrder(guide, concepts, sqlCoding);
    }
}
