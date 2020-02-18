package com.mycompany.hw_l09_spring_orm_jpa;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Author;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Comment;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Genre;
import com.mycompany.hw_l09_spring_orm_jpa.repositories.CommentRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(CommentRepositoryImpl.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CommentRepositoryImplTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author pratt = new Author(1, "Philip", "Pratt");
    private final Book guide = new Book(1, "A Guide to SQL").author(pratt).genre(genre);

    private final Comment commentOne = new Comment(1, "First comment - Guide").book(guide);
    private final Comment commentTwo = new Comment(2, "Second comment - Guide").book(guide);
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private CommentRepositoryImpl repository;

    @Autowired
    private TestEntityManager em;

    @Test
    void getCommentsByBookId() {
        var comments = repository.getCommentsByBookId(guide.id());
        assertThat(comments).containsExactlyInAnyOrder(commentOne, commentTwo);
    }

    @Test
    void getCommentsByNonExistingBookId() {
        var comments = repository.getCommentsByBookId(NON_EXISTING_ID);
        assertThat(comments).isEmpty();
    }

    @Test
    void addCommentByBookId() {
        var comment = new Comment("Test comment");
        long id = repository.addCommentByBookId(guide.id(), comment);
        assertThat(id).isEqualTo(4);

        em.clear();
        comment.book(guide);
        var commentInDb = em.find(Comment.class, id);
        assertThat(comment).isEqualTo(commentInDb);
    }

    @Test
    void addCommentByNonExistingBookId() {
        var comment = new Comment("Test comment");
        long id = repository.addCommentByBookId(NON_EXISTING_ID, comment);
        assertThat(id).isEqualTo(0);
    }

    @Test
    void deleteCommentByBookId() {
        boolean isDeleted = repository.deleteCommentByBookId(guide.id(), commentOne);
        assertTrue(isDeleted, "Comment is not deleted by bookId");

        em.clear();
        var comment = em.find(Comment.class, commentOne.id());
        assertNull(comment, "Comment is not deleted form DB");
    }

    @Test
    void deleteCommentByNonExistingBookId() {
        boolean isDeleted = repository.deleteCommentByBookId(NON_EXISTING_ID, commentOne);
        assertFalse(isDeleted, "Comment is deleted by non-existing bookId");

        em.clear();
        var comment = em.find(Comment.class, commentOne.id());
        assertNotNull(comment, "Comment is deleted form DB");
    }

    @Test
    void deleteNonExistingComment() {
        var comment = new Comment("Non existing");
        boolean isDeleted = repository.deleteCommentByBookId(guide.id(), comment);
        assertFalse(isDeleted, "Non-existing comment is deleted by bookId");
    }
}
