package com.mycompany.hw_l09_spring_orm_jpa;

import com.mycompany.hw_l09_spring_orm_jpa.dao.AuthorRepositoryImpl;
import com.mycompany.hw_l09_spring_orm_jpa.dao.BookRepositoryImpl;
import com.mycompany.hw_l09_spring_orm_jpa.dao.GenreRepositoryImpl;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Author;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Genre;
import com.mycompany.hw_l09_spring_orm_jpa.service.BookDbServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@JdbcTest
@Import({AuthorRepositoryImpl.class, BookRepositoryImpl.class,
        GenreRepositoryImpl.class, BookDbServiceImpl.class})
public class BookDbServiceImplTests {

    @MockBean
    private GenreRepositoryImpl genreDaoJdbc;

    @MockBean
    private AuthorRepositoryImpl authorDaoJdbc;

    @MockBean
    private BookRepositoryImpl bookDaoJdbc;

    @Autowired
    private BookDbServiceImpl bookDbService;

    @Test
    void insertBookWithAuthorAndGenre() {
        var genre = new Genre("Programming");
        var author = new Author("Benjamin", "Evans");
        var book = new Book("test").author(author).genre(genre);
        bookDbService.insert(book);

        verify(genreDaoJdbc, times(1)).insert(genre);
        verify(authorDaoJdbc, times(1)).insert(author);
        verify(bookDaoJdbc, times(1)).insert(book);
    }

    @Test
    void insertBookWithNullAuthorAndGenre() {
        var book = new Book("test");
        bookDbService.insert(book);

        verify(genreDaoJdbc, times(0)).insert(any());
        verify(authorDaoJdbc, times(0)).insert(any());
        verify(bookDaoJdbc, times(1)).insert(book);
    }
}
