package com.mycompany.hw_l11_spring_data_jpa.service;

import com.mycompany.hw_l11_spring_data_jpa.domain.Book;
import org.springframework.boot.test.mock.mockito.MockBean;

//@DataJpaTest
//@Import({BookRepositoryImpl.class, BookDbServiceImpl.class})
public class BookDbServiceImplTests {

    //@MockBean
    private BookDbServiceImpl bookDbService;

    //  @Test
    void updateBookWithTitle() {
        var book = new Book(1, "newTitle");
        //bookDbService.update(book),
        //        "Book title is not updated");
    }

    //   @Test
    void updateBookWithoutTitle() {
        var book = new Book(1, null);
        //      assertFalse(bookDbService.update(book),
        //              "Book title is updated");
    }
}
