package com.mycompany.hw_l13_spring_data_nosql.service;

import com.mycompany.hw_l13_spring_data_nosql.domain.Author;
import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import com.mycompany.hw_l13_spring_data_nosql.domain.Genre;
import com.mycompany.hw_l13_spring_data_nosql.repositories.AuthorRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.BookRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.CommentRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Import(BookDbServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class BookDbServiceImplTests {

    private final Genre genre = new Genre("Computers & Technology").setId("1");
    private final Author author = new Author("Philip", "Pratt").setId("1");
    private final Book book = new Book("A Guide to SQL").setId("1");

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private GenreRepository genreRepository;

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
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        var captor = ArgumentCaptor.forClass(Book.class);

        var newTitle = "New title";
        bookDbService.updateTitle(book.getId(), newTitle);
        verify(bookRepository).save(captor.capture());
        assertThat(captor.getValue().getTitle()).isEqualTo(newTitle);
    }

    @Test
    void updateBookWithNullTitle() {
        bookDbService.updateTitle(book.getId(), null);
        verify(bookRepository, never()).save(book);
    }

    @Test
    void deleteBookById_NoRelations() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        bookDbService.deleteById(book.getId());
        verify(bookRepository, atMostOnce()).deleteById(book.getId());
        verify(authorRepository, never()).deleteById(anyString());
        verify(genreRepository, never()).deleteById(anyString());
        verify(commentRepository, atMostOnce()).deleteAllByBookId(book.getId());
    }

    @Test
    void deleteBookById_AuthorRelation() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        book.setAuthor(author);
        bookDbService.deleteById(book.getId());
        verify(bookRepository, atMostOnce()).deleteById(book.getId());
        verify(authorRepository, atMostOnce()).deleteById(author.getId());
    }

    @Test
    void deleteBookById_GenreRelation() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        book.setGenre(genre);
        bookDbService.deleteById(book.getId());
        verify(bookRepository, atMostOnce()).deleteById(book.getId());
        verify(genreRepository, atMostOnce()).deleteById(genre.getId());
    }
}
