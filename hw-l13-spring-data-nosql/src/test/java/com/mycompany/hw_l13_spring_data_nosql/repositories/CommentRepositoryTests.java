package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.domain.Author;
import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import com.mycompany.hw_l13_spring_data_nosql.domain.Comment;
import com.mycompany.hw_l13_spring_data_nosql.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class CommentRepositoryTests {

    private final Genre genre = new Genre("1", "Computers & Technology");
    private final Author author = new Author("1", "Philip", "Pratt");
    private final Book book = new Book("1", "A Guide to SQL", author, genre);

    private final Comment commentOne = new Comment("1", "First comment - Guide", book);
    private final Comment commentTwo = new Comment("2", "Second comment - Guide", book);
    private static final String NON_EXISTING_ID = "50";

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findByBookId() {
        var comments = repository.findByBookId(author.getId());
        assertThat(comments).containsExactlyInAnyOrder(commentOne, commentTwo);
    }

    @Test
    void findByNonExistingBookId() {
        var comments = repository.findByBookId(NON_EXISTING_ID);
        assertThat(comments).isEmpty();
    }

    @Test
    void deleteByBookId() {
        repository.deleteByBookId(author.getId(), commentOne);
        var comment = em.find(Comment.class, commentOne.getId());
        assertNull(comment, "Comment is not deleted form DB");
    }

    @Test
    void deleteByNonExistingBookId() {
        repository.deleteByBookId(NON_EXISTING_ID, commentOne);
        var comment = em.find(Comment.class, commentOne.getId());
        assertNotNull(comment, "Comment is deleted form DB");
    }

    @Test
    void deleteAllByBookId() {
        repository.deleteAllByBookId(book.getId());
        var comments = repository.findByBookId(book.getId());
        assertThat(comments).isEmpty();
    }
}
