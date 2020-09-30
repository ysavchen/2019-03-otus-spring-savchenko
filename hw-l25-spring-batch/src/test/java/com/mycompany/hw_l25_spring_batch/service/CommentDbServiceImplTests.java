package com.mycompany.hw_l25_spring_batch.service;

import com.mycompany.hw_l25_spring_batch.domain.Book;
import com.mycompany.hw_l25_spring_batch.domain.Comment;
import com.mycompany.hw_l25_spring_batch.repositories.BookRepository;
import com.mycompany.hw_l25_spring_batch.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import(CommentDbServiceImpl.class)
public class CommentDbServiceImplTests {

    private final Book book = new Book("A Guide to SQL").setId("1");
    private final Comment comment = new Comment("First comment - Guide", book).setId("1");
    private static final String NON_EXISTING_ID = "50";
    private static final String EMPTY_STRING = "";

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentDbServiceImpl commentDbService;

    @Test
    void addCommentByBookId() {
        var comment = new Comment("Test comment").setId("1");
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
        assertThat(id).isEqualTo(EMPTY_STRING);
    }

    @Test
    void deleteByBookId() {
        when(commentRepository.findByBookId(book.getId())).thenReturn(List.of(comment));

        commentDbService.deleteCommentByBookId(book.getId(), comment);
        verify(commentRepository, atMostOnce()).save(comment);
    }

    @Test
    void deleteByNonExistingBookId() {
        when(commentRepository.findByBookId(book.getId())).thenReturn(emptyList());

        commentDbService.deleteCommentByBookId(book.getId(), comment);
        verify(commentRepository, never()).save(comment);
    }
}
