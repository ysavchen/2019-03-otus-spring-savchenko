package com.mycompany.hw_l11_spring_data_jpa.service;

import com.mycompany.hw_l11_spring_data_jpa.domain.Book;
import com.mycompany.hw_l11_spring_data_jpa.domain.Comment;
import com.mycompany.hw_l11_spring_data_jpa.repositories.BookRepository;
import com.mycompany.hw_l11_spring_data_jpa.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
@Import(CommentDbServiceImpl.class)
public class CommentDbServiceImplTests {

    private final Book guide = new Book(1, "A Guide to SQL");
    private static final long NON_EXISTING_ID = 50;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentDbServiceImpl commentDbService;

    @Test
    void addCommentByBookId() {
        var comment = new Comment(1, "Test comment");
        when(bookRepository.findById(guide.getId())).thenReturn(Optional.of(guide));
        when(commentRepository.save(comment)).thenReturn(comment);

        commentDbService.addCommentByBookId(guide.getId(), comment);
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void addCommentByNonExistingBookId() {
        var comment = new Comment("Test comment");
        long id = commentDbService.addCommentByBookId(NON_EXISTING_ID, comment);
        verify(commentRepository, never()).save(comment);
        assertThat(id).isEqualTo(0);
    }
}
