package com.mycompany.hw_l11_spring_data_jpa.service;

import com.mycompany.hw_l11_spring_data_jpa.domain.Author;
import com.mycompany.hw_l11_spring_data_jpa.domain.Book;
import com.mycompany.hw_l11_spring_data_jpa.domain.Comment;
import com.mycompany.hw_l11_spring_data_jpa.domain.Genre;
import com.mycompany.hw_l11_spring_data_jpa.repositories.AuthorRepository;
import com.mycompany.hw_l11_spring_data_jpa.repositories.BookRepository;
import com.mycompany.hw_l11_spring_data_jpa.repositories.CommentRepository;
import com.mycompany.hw_l11_spring_data_jpa.repositories.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@DataJpaTest
@Import(BookDbServiceImpl.class)
public class BookDbServiceImplTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author author = new Author(1, "Philip", "Pratt");
    private final Comment comment = new Comment(1, "First comment - Guide");
    private final Book book = new Book(1, "A Guide to SQL");

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
    void updateTitle() {
        var newTitle = "New title";
        bookDbService.updateTitle(book.getId(), newTitle);
        verify(bookRepository, atMostOnce()).updateTitle(book.getId(), newTitle);
    }

    @Test
    void updateBookWithNullTitle() {
        bookDbService.updateTitle(book.getId(), null);
        verify(bookRepository, never()).updateTitle(book.getId(), null);
    }

    @Test
    void deleteBookById_NoRelations() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        bookDbService.deleteById(book.getId());
        verify(bookRepository, atMostOnce()).deleteById(book.getId());
        verify(authorRepository, never()).deleteById(anyLong());
        verify(genreRepository, never()).deleteById(anyLong());
        verify(commentRepository, never()).deleteAllByBookId(anyLong());
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

    @Test
    void deleteBookById_CommentsRelation() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(commentRepository.findByBookId(book.getId())).thenReturn(List.of(comment));

        bookDbService.deleteById(book.getId());
        verify(bookRepository, atMostOnce()).deleteById(book.getId());
        verify(commentRepository, atMostOnce()).deleteAllByBookId(book.getId());
    }
}
