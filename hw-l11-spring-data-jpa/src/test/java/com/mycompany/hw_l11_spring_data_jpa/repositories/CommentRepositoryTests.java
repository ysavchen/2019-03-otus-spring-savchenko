package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Author;
import com.mycompany.hw_l11_spring_data_jpa.domain.Book;
import com.mycompany.hw_l11_spring_data_jpa.domain.Comment;
import com.mycompany.hw_l11_spring_data_jpa.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CommentRepositoryTests {

    private static final Genre GENRE = new Genre(1, "Computers & Technology");
    private static final Author PRATT_AUTHOR = new Author(1, "Philip", "Pratt");
    private static final Book GUIDE_BOOK = new Book(1, "A Guide to SQL", PRATT_AUTHOR, GENRE);

    private static final Comment COMMENT_ONE = new Comment(1, "First comment - Guide", GUIDE_BOOK);
    private static final Comment COMMENT_TWO = new Comment(2, "Second comment - Guide", GUIDE_BOOK);
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findByBookId() {
        var comments = repository.findByBookId(PRATT_AUTHOR.getId());
        assertThat(comments).containsExactlyInAnyOrder(COMMENT_ONE, COMMENT_TWO);
    }

    @Test
    void findByNonExistingBookId() {
        var comments = repository.findByBookId(NON_EXISTING_ID);
        assertThat(comments).isEmpty();
    }

    @Test
    void deleteByBookId() {
        repository.deleteByBookId(PRATT_AUTHOR.getId(), COMMENT_ONE);
        var comment = em.find(Comment.class, COMMENT_ONE.getId());
        assertNull(comment, "Comment is not deleted form DB");
    }

    @Test
    void deleteByNonExistingBookId() {
        repository.deleteByBookId(NON_EXISTING_ID, COMMENT_ONE);
        var comment = em.find(Comment.class, COMMENT_ONE.getId());
        assertNotNull(comment, "Comment is deleted form DB");
    }

    @Test
    void deleteAllByBookId() {
        repository.deleteAllByBookId(GUIDE_BOOK.getId());
        var comments = repository.findByBookId(GUIDE_BOOK.getId());
        assertThat(comments).isEmpty();
    }
}
