package com.mycompany.hw_l16_spring_mvc_view.service;

import com.mycompany.hw_l16_spring_mvc_view.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDbService {

    Book save(Book book);

    Optional<Book> getById(long id);

    List<Book> getAllBooks();

    void updateTitle(long id, String title);

    void deleteById(long id);
}
