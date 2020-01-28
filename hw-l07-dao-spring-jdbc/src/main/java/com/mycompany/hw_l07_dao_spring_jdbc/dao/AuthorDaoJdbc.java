package com.mycompany.hw_l07_dao_spring_jdbc.dao;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(Author author) {
        return 0;
    }

    @Override
    public void updateWithBookRelation(long authorId, long bookId) {
        Map<String, Object> params = Map.of("authorId", authorId, "bookId", bookId);
        jdbc.update("update authors set book_id = :bookId where id = :authorId", params);
    }


    @Override
    public void deleteById(long id) {

    }
}
