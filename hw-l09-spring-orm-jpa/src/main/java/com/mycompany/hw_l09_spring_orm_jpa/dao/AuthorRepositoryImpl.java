package com.mycompany.hw_l09_spring_orm_jpa.dao;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Author;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;
import com.mycompany.hw_l09_spring_orm_jpa.exception.NoIdException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
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
            Author author = jdbc.query(
                    "select a.id as authorId, a.name as authorName, a.surname as authorSurname, " +
                            "b.id as bookId, b.title as title " +
                            "from authors a " +
                            "left join books b on b.author_id = a.id " +
                            "where a.id = :id", params, new AuthorExtractor()
            );
            return Optional.ofNullable(author);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    private static class AuthorExtractor implements ResultSetExtractor<Author> {

        @Override
        public Author extractData(ResultSet rs) throws SQLException, DataAccessException {
            Author author = new Author();

            if (rs.next()) {
                author.setId(rs.getLong("authorId"));
                author.setName(rs.getString("authorName"));
                author.setSurname(rs.getString("authorSurName"));

                do {
                    var bookId = rs.getLong("bookId");
                    var titleId = rs.getString("title");

                    if (bookId != 0 && titleId != null) {
                        author.getBooks().add(new Book(bookId, titleId));
                    }
                } while (rs.next());
            } else {
                author = null;
            }
            return author;
        }
    }
}
