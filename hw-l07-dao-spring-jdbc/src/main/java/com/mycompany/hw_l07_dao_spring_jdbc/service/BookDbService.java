package com.mycompany.hw_l07_dao_spring_jdbc.service;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;

public interface BookDbService {

    long insert(Book book);

    Book getById(long id);

    void update(Book book);

    void delete(Book book);
}
