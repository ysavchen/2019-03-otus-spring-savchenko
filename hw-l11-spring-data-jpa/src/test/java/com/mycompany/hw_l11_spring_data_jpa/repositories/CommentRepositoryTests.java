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
    void getCommentsByBookId() {
        var comments = repository.findByBookId(guide.getId());
        assertThat(comments).containsExactlyInAnyOrder(commentOne, commentTwo);
    }

    @Test
    void getCommentsByNonExistingBookId() {
        var comments = repository.findByBookId(NON_EXISTING_ID);
        assertThat(comments).isEmpty();
    }

    @Test
    void addCommentByBookId() {
        var comment = new Comment("Test comment");
        //long id = repository.addCommentByBookId(guide.id(), comment);
        //assertThat(id).isEqualTo(4);

        //em.clear();
        //comment.book(guide);
        //var commentInDb = em.find(Comment.class, id);
        //assertThat(comment).isEqualTo(commentInDb);
    }

    @Test
    void addCommentByNonExistingBookId() {
        var comment = new Comment("Test comment");
        //long id = repository.addCommentByBookId(NON_EXISTING_ID, comment);
        //assertThat(id).isEqualTo(0);
    }

    @Test
    void deleteCommentByBookId() {
        //boolean isDeleted = repository.deleteCommentByBookId(guide.id(), commentOne);
        //assertTrue(isDeleted, "Comment is not deleted by bookId");

        //em.clear();
        //var comment = em.find(Comment.class, commentOne.id());
        //assertNull(comment, "Comment is not deleted form DB");
    }

    @Test
    void deleteCommentByNonExistingBookId() {
        //boolean isDeleted = repository.deleteCommentByBookId(NON_EXISTING_ID, commentOne);
        //assertFalse(isDeleted, "Comment is deleted by non-existing bookId");

        //em.clear();
        //var comment = em.find(Comment.class, commentOne.id());
        //assertNotNull(comment, "Comment is deleted form DB");
    }

    @Test
    void deleteNonExistingComment() {
        //var comment = new Comment("Non existing");
        //boolean isDeleted = repository.deleteCommentByBookId(guide.id(), comment);
        //assertFalse(isDeleted, "Non-existing comment is deleted by bookId");
    }
}
