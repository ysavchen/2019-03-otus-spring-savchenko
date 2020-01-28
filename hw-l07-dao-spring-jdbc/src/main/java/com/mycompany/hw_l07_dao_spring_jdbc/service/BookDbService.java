package com.mycompany.hw_l07_dao_spring_jdbc.service;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;

import java.util.Optional;

public interface BookDbService {

    long insert(Book book);

    Optional<Book> getById(long id);

    void update(Book book);

    void deleteById(long id);
}