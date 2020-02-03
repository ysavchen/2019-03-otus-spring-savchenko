package com.mycompany.hw_l09_spring_orm_jpa.repositories;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Author;
import com.mycompany.hw_l09_spring_orm_jpa.exception.NoIdException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(@NonNull Author author) {
        var keyHolder = new GeneratedKeyHolder();
        var params = new MapSqlParameterSource()
                .addValue("name", author.getName())
                .addValue("surname", author.getSurname());

        jdbc.update("insert into authors (name, surname) values (:name, :surname)", params, keyHolder);

        var number = keyHolder.getKey();
        if (number == null) {
            throw new NoIdException("No id for author: " + author.getName() + " " + author.getSurname());
        }
        return number.longValue();
    }

    @Override
    public Optional<Author> getById(long id) {
        Map<String, Object> params = Map.of("id", id);
        try {
            Author author = jdbc.queryForObject(
                    "select a.id as authorId, a.name as authorName, a.surname as authorSurname, " +
                            "from authors a " +
                            "where a.id = :id", params, new AuthorMapper()
            );
            return Optional.ofNullable(author);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            long id = rs.getLong("authorId");
            String name = rs.getString("authorName");
            String surname = rs.getString("authorSurName");
            return new Author(id, name, surname);
        }
    }
}
