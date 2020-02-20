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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    void saveBook() {
        var book = new Book("test");
        long id = bookRepository.save(book).getId();
        assertEquals(4, id, "Invalid id for an saved Book");
    }

    @Test
    void saveBookWithGenre() {
        var genre = new Genre("test genre");
        var bookWithGenre = new Book("test").setGenre(genre);
        assertEquals(4, bookRepository.save(bookWithGenre).getId(),
                "Book with genre is not saved");
    }

    @Test
    void saveBookWithAuthor() {
        var testAuthor = new Author("testName", "testSurname");
        var bookWithAuthor = new Book("test").setAuthor(testAuthor);
        assertEquals(4, bookRepository.save(bookWithAuthor).getId(),
                "Book with author is not saved");

    }

    @Test
    void saveBookWithGenreAndAuthor() {
        var testGenre = new Genre("test genre");
        var testAuthor = new Author("testName", "testSurname");
        var bookWithBoth = new Book("test").setAuthor(testAuthor).setGenre(testGenre);

        assertEquals(4, bookRepository.save(bookWithBoth).getId(),
                "Book with genre and author is not saved");
    }

    @Test
    void findBookById() {
        assertThat(bookRepository.findById(guide.getId())).get()
                .hasFieldOrPropertyWithValue("id", guide.getId())
                .hasFieldOrPropertyWithValue("title", guide.getTitle())
                .hasFieldOrPropertyWithValue("author", guide.getAuthor())
                .hasFieldOrPropertyWithValue("genre", guide.getGenre());
    }

    @Test
    void findBookByNonExistingId() {
        assertThat(bookRepository.findById(NON_EXISTING_ID)).isEmpty();
    }

    @Test
    void findBookWithGenre() {
        var testGenre = new Genre("test genre");
        var book = new Book("test").setGenre(testGenre);
        long id = bookRepository.save(book).getId();

        em.clear();
        assertThat(bookRepository.findById(id)).get()
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("title", book.getTitle())
                .hasFieldOrPropertyWithValue("author", null)
                .hasFieldOrPropertyWithValue("genre", testGenre);
    }

    @Test
    void findBookWithAuthor() {
        var testAuthor = new Author("testName", "testSurname");
        var book = new Book("test").setAuthor(testAuthor);
        long id = bookRepository.save(book).getId();

        em.clear();
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
        var book = new Book("test").setAuthor(testAuthor).setGenre(testGenre);

        em.clear();
        long id = bookRepository.save(book).getId();
        assertThat(bookRepository.findById(id)).get()
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("title", book.getTitle())
                .hasFieldOrPropertyWithValue("author", testAuthor)
                .hasFieldOrPropertyWithValue("genre", testGenre);
    }

    @Test
    void updateBookTitle() {
        var newTitle = "newTitle";
        bookRepository.updateTitle(guide.getId(), newTitle);

        em.clear();
        assertThat(bookRepository.findById(guide.getId())).get()
                .hasFieldOrPropertyWithValue("title", newTitle);
    }

    @Test
    void deleteById() {
        bookRepository.deleteById(guide.getId());
        assertNull(em.find(Book.class, guide.getId()),
                "Book is not deleted by id");
    }

    @Test
    void findBooksByAuthorId() {
        var books = bookRepository.findByAuthorId(pratt.getId());
        assertThat(books).containsExactlyInAnyOrder(guide, concepts);
    }

    @Test
    void findBooksByNonExistingAuthorId() {
        var books = bookRepository.findByAuthorId(NON_EXISTING_ID);
        assertThat(books).isEmpty();
    }

    @Test
    void findBooksByGenreId() {
        var books = bookRepository.findByGenreId(genre.getId());
        assertThat(books).containsExactlyInAnyOrder(guide, concepts, sqlCoding);
    }

    @Test
    void findBooksByNonExistingGenreId() {
        var books = bookRepository.findByGenreId(NON_EXISTING_ID);
        assertThat(books).isEmpty();
    }

    @Test
    void findAllBooks() {
        var books = bookRepository.findAll();
        assertThat(books).containsExactlyInAnyOrder(guide, concepts, sqlCoding);
    }
}
