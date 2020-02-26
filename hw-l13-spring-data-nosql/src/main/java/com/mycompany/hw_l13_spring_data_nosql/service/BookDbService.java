package com.mycompany.hw_l13_spring_data_nosql.service;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDbService {

    long save(Book book);

    Optional<Book> getById(long id);

    List<Book> getBooksByAuthorId(long id);

    List<Book> getAllBooks();

    void updateTitle(long id, String title);

    void deleteById(long id);
}
