package com.mycompany.hw_l07_dao_spring_jdbc.dao;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;

public interface AuthorDao {

    long insert(Author author);

    Author getById(long id);

    void deleteById(long id);
}
