package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.AbstractRepositoryTest;
import com.mycompany.hw_l13_spring_data_nosql.domain.Author;
import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import com.mycompany.hw_l13_spring_data_nosql.domain.Comment;
import com.mycompany.hw_l13_spring_data_nosql.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentRepositoryTests extends AbstractRepositoryTest {

    private final Genre genre = new Genre("Computers & Technology").setId("1");
    private final Author author = new Author("Philip", "Pratt").setId("1");
    private final Book book = new Book("A Guide to SQL", author, genre).setId("1");

    private final Comment commentOne = new Comment("First comment - Guide", book).setId("1");
    private final Comment commentTwo = new Comment("Second comment - Guide", book).setId("2");
    private static final String NON_EXISTING_ID = "50";

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findCommentsByBookId() {
        var comments = commentRepository.findByBookId(book.getId());
        assertThat(comments).containsExactlyInAnyOrder(commentOne, commentTwo);
    }

    @Test
    void findByNonExistingBookId() {
        var comments = commentRepository.findByBookId(NON_EXISTING_ID);
        assertThat(comments).isEmpty();
    }

    @Test
    void deleteAllByBookId() {
        commentRepository.deleteAllByBookId(book.getId());
        var comments = commentRepository.findByBookId(book.getId());
        assertThat(comments).isEmpty();
    }
}
