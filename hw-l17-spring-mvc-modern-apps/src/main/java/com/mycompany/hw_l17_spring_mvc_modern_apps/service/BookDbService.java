package com.mycompany.hw_l17_spring_mvc_modern_apps.service;

import com.mycompany.hw_l17_spring_mvc_modern_apps.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDbService {

    Book save(Book book);

    Optional<Book> getById(long id);

    List<Book> getAllBooks();

    void updateTitle(long id, String title);

    void deleteById(long id);
}
