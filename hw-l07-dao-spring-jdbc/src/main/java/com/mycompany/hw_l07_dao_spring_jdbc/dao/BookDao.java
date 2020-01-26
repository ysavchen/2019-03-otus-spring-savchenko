package com.mycompany.hw_l07_dao_spring_jdbc.dao;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;

import java.util.List;

public interface BookDao {

    long insert(Book book);

    Book getById(long id);

    Book getBookByTitle(String title);

    void update(Book book);

    void deleteById(long id);

    List<Book> getAll();
}
