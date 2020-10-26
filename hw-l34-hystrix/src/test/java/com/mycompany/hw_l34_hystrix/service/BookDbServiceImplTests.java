package com.mycompany.hw_l34_hystrix.service;

import com.mycompany.hw_l34_hystrix.domain.Author;
import com.mycompany.hw_l34_hystrix.domain.Book;
import com.mycompany.hw_l34_hystrix.domain.Genre;
import com.mycompany.hw_l34_hystrix.repositories.AuthorRepository;
import com.mycompany.hw_l34_hystrix.repositories.BookRepository;
import com.mycompany.hw_l34_hystrix.repositories.GenreRepository;
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
    private final Book book = new Book(1, "A Guide to SQL", author, genre);

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
    void deleteBookById() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        bookDbService.deleteById(book.getId());
        verify(bookRepository, times(1)).deleteById(book.getId());
        verify(authorRepository, never()).deleteById(anyLong());
        verify(genreRepository, never()).deleteById(anyLong());
    }
}
