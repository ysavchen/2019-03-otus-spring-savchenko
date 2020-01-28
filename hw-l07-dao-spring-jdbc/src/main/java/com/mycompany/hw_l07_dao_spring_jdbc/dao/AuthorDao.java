package com.mycompany.hw_l07_dao_spring_jdbc.dao;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;

public interface AuthorDao {

    long insert(Author author);

    void updateWithBookRelation(long authorId, long bookId);

    void deleteById(long id);
}
