package com.mycompany.hw_l17_spring_mvc_modern_apps.service;

import com.mycompany.hw_l17_spring_mvc_modern_apps.domain.Author;
import com.mycompany.hw_l17_spring_mvc_modern_apps.domain.Book;
import com.mycompany.hw_l17_spring_mvc_modern_apps.domain.Genre;
import com.mycompany.hw_l17_spring_mvc_modern_apps.repositories.AuthorRepository;
import com.mycompany.hw_l17_spring_mvc_modern_apps.repositories.BookRepository;
import com.mycompany.hw_l17_spring_mvc_modern_apps.repositories.GenreRepository;
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

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author author = new Author(1, "Philip", "Pratt");
    private final Book book = new Book(1, "A Guide to SQL");

    @MockBean
    private BookRepository bookRepository;

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
