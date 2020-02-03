package com.mycompany.hw_l09_spring_orm_jpa.service;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;

import java.util.Optional;

public interface BookDbService {

    long insert(Book book);

    Optional<Book> getById(long id);

    boolean update(Book book);

    boolean deleteById(long id);
}
