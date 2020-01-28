package com.mycompany.hw_l07_dao_spring_jdbc.dao;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;

import java.util.Optional;

public interface BookDao {

    /**
     * Inserts a book without relations.
     */
    long insert(Book book);

    void updateWithAuthorRelation(long bookId, long authorId);

    void updateWithGenreRelation(long bookId, long genreId);

    Optional<Book> getById(long id);

    void update(Book book);

    void deleteById(long id);
}
