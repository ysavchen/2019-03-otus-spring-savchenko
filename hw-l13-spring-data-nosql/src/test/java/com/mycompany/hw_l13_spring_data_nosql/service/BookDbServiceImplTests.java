package com.mycompany.hw_l13_spring_data_nosql.service;

import com.mycompany.hw_l13_spring_data_nosql.domain.Author;
import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import com.mycompany.hw_l13_spring_data_nosql.repositories.AuthorRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.BookRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@Import(BookDbServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class BookDbServiceImplTests {

    private final Author author = new Author("Philip", "Pratt").setId("1");
    private final Book book = new Book("A Guide to SQL").setId("1");

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private BookDbServiceImpl bookDbService;

    @Test
    void saveWithAuthor() {
        when(bookRepository.save(book)).thenReturn(book);

        book.setAuthor(author);
        bookDbService.save(book);
        verify(authorRepository, atMostOnce()).save(author);
    }

    @Test
    void saveWithoutAuthor() {
        when(bookRepository.save(book)).thenReturn(book);

        book.setAuthor(null);
        bookDbService.save(book);
        verify(authorRepository, never()).save(author);
    }

    @Test
    void updateTitle() {
        var title = "new title";
        bookDbService.updateTitle(book.getId(), title);
        verify(bookRepository, times(1)).updateTitle(book.getId(), title);
    }

    @Test
    void updateBookWithNullTitle() {
        bookDbService.updateTitle(book.getId(), null);
        verify(bookRepository, never()).save(book);
    }

    @Test
    void deleteBookById() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        bookDbService.deleteById(book.getId());
        verify(bookRepository, times(1)).deleteById(book.getId());
        verify(commentRepository, times(1)).deleteAllByBookId(book.getId());
    }
}
