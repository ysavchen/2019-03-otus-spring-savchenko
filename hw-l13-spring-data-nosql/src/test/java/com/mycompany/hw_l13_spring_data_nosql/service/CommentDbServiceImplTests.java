package com.mycompany.hw_l13_spring_data_nosql.service;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import com.mycompany.hw_l13_spring_data_nosql.domain.Comment;
import com.mycompany.hw_l13_spring_data_nosql.repositories.BookRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import(CommentDbServiceImpl.class)
public class CommentDbServiceImplTests {

    private final Book book = new Book("1", "A Guide to SQL");
    private static final String NON_EXISTING_ID = "50";

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentDbServiceImpl commentDbService;

    @Test
    void addCommentByBookId() {
        var comment = new Comment("1", "Test comment");
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(commentRepository.save(comment)).thenReturn(comment);

        commentDbService.addCommentByBookId(book.getId(), comment);
        verify(commentRepository, atMostOnce()).save(comment);
    }

    @Test
    void addCommentByNonExistingBookId() {
        var comment = new Comment("Test comment");
        String id = commentDbService.addCommentByBookId(NON_EXISTING_ID, comment);
        verify(commentRepository, never()).save(comment);
        assertThat(id).isEqualTo(0);
    }
}
