package com.mycompany.hw_l09_spring_orm_jpa;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;
import com.mycompany.hw_l09_spring_orm_jpa.repositories.BookRepositoryImpl;
import com.mycompany.hw_l09_spring_orm_jpa.service.BookDbServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@Import({BookRepositoryImpl.class, BookDbServiceImpl.class})
public class BookDbServiceImplTests {

    @Autowired
    private BookDbServiceImpl bookDbService;

    @Test
    void updateBookWithTitle() {
        var book = new Book(1, "newTitle");
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
