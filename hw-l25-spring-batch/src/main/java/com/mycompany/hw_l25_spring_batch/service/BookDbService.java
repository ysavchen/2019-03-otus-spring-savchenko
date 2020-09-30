package com.mycompany.hw_l25_spring_batch.service;

import com.mycompany.hw_l25_spring_batch.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDbService {

    String save(Book book);

    Optional<Book> getById(String id);

    List<Book> getBooksByAuthorId(String id);

    List<Book> getAllBooks();

    void updateTitle(String id, String title);

    void deleteById(String id);
}
