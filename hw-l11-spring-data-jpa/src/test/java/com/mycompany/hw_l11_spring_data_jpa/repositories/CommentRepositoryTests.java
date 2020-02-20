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

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author pratt = new Author(1, "Philip", "Pratt");
    private final Book guide = new Book(1, "A Guide to SQL", pratt, genre);

    private final Comment commentOne = new Comment(1, "First comment - Guide", guide);
    private final Comment commentTwo = new Comment(2, "Second comment - Guide", guide);
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findByBookId() {
        var comments = repository.findByBookId(guide.getId());
        assertThat(comments).containsExactlyInAnyOrder(commentOne, commentTwo);
    }

    @Test
    void findByNonExistingBookId() {
        var comments = repository.findByBookId(NON_EXISTING_ID);
        assertThat(comments).isEmpty();
    }

    @Test
    void deleteByBookId() {
        repository.deleteByBookId(guide.getId(), commentOne);
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
        repository.deleteAllByBookId(guide.getId());
        var comments = repository.findByBookId(guide.getId());
        assertThat(comments).isEmpty();
    }
}
