package com.mycompany.hw_l34_srping_cloud.service;

import com.mycompany.hw_l34_srping_cloud.domain.Author;
import com.mycompany.hw_l34_srping_cloud.domain.Book;
import com.mycompany.hw_l34_srping_cloud.domain.Genre;
import com.mycompany.hw_l34_srping_cloud.repositories.BookRepository;
import com.mycompany.hw_l34_srping_cloud.repositories.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@Import(BookServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class BookServiceImplTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author author = new Author(1, "Philip", "Pratt");
    private final Book book = new Book(1, "A Guide to SQL", author, genre);

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void updateTitle() {
        var newTitle = "New title";
        bookService.updateTitle(book.getId(), newTitle);
        verify(bookRepository, atMostOnce()).updateTitle(book.getId(), newTitle);
    }

    @Test
    void updateBookWithNullTitle() {
        bookService.updateTitle(book.getId(), null);
        verify(bookRepository, never()).updateTitle(book.getId(), null);
    }

    @Test
    void deleteBookById() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        bookService.deleteById(book.getId());
        verify(bookRepository, times(1)).deleteById(book.getId());
        verify(genreRepository, never()).deleteById(anyLong());
    }
}
