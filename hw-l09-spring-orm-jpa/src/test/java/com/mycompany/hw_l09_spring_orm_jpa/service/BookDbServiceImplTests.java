package com.mycompany.hw_l09_spring_orm_jpa.service;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;
import com.mycompany.hw_l09_spring_orm_jpa.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DataJpaTest
@Import(BookDbServiceImpl.class)
public class BookDbServiceImplTests {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookDbServiceImpl bookDbService;

    @Test
    void updateBookWithTitle() {
        var book = new Book(1, "newTitle");
        when(bookRepository.update(book)).thenReturn(true);
        assertTrue(bookDbService.update(book),
                "Book title is not updated");
    }

    @Test
    void updateBookWithoutTitle() {
        var book = new Book(1, null);
        assertFalse(bookDbService.update(book),
                "Book title is updated");
    }
}
