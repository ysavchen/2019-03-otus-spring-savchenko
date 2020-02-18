package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    long insert(Book book);

    Optional<Book> getById(long id);

    List<Book> getBooksByAuthorId(long id);

    List<Book> getAllBooks();

    boolean update(Book book);

    boolean deleteById(long id);
}
