package com.mycompany.hw_l07_dao_spring_jdbc.dao;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Genre;
import com.mycompany.hw_l07_dao_spring_jdbc.exception.NoIdException;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(@NonNull Book book) {
        var keyHolder = new GeneratedKeyHolder();
        var params = new MapSqlParameterSource()
                .addValue("title", book.title());
        if (book.author() != null) {
            params.addValue("author_id", book.author().getId());
        } else {
            params.addValue("author_id", null);
        }
        if (book.genre() != null) {
            params.addValue("genre_id", book.genre().getId());
        } else {
            params.addValue("genre_id", null);
        }

        jdbc.update("insert into books (title, author_id, genre_id) " +
                "values (:title, :author_id, :genre_id)", params, keyHolder);

        var number = keyHolder.getKey();
        if (number == null) {
            throw new NoIdException("No id for book with title = " + book.title());
        }
        return number.longValue();
    }

    @Override
    public Optional<Book> getById(long id) {
        Map<String, Object> params = Map.of("id", id);
        try {
            Book book = jdbc.queryForObject(
                    "select b.id as bookId, b.title as title, " +
                            "a.id as authorId, a.name as authorName, a.surname as authorSurname, " +
                            "g.id as genreId, g.name as genreName " +
                            "from books b " +
                            "left join authors a on b.author_id = a.id " +
                            "left join genres g on b.genre_id = g.id " +
                            "where b.id = :id", params, new BookMapper()
            );
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> getBooksByAuthorId(long id) {
        Map<String, Object> params = Map.of("id", id);
        try {
            return jdbc.query(
                    "select b.id as bookId, b.title as title, " +
                            "a.id as authorId, a.name as authorName, a.surname as authorSurname, " +
                            "g.id as genreId, g.name as genreName " +
                            "from books b " +
                            "left join authors a on b.author_id = a.id " +
                            "left join genres g on b.genre_id = g.id " +
                            "where a.id = :id", params, new BookMapper()
            );
        } catch (EmptyResultDataAccessException ex) {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean update(@NonNull Book book) {
        Map<String, Object> params = Map.of("id", book.id(), "title", book.title());
        int rows = jdbc.update("update books set title = :title where id = :id", params);
        return rows > 0;
    }

    @Override
    public boolean deleteById(long id) {
        Map<String, Object> params = Map.of("id", id);
        int rows = jdbc.update("delete from books where id = :id", params);
        return rows > 0;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            long authorId = resultSet.getLong("authorId");
            String authorName = resultSet.getString("authorName");
            String authorSurname = resultSet.getString("authorSurname");
            long genreId = resultSet.getLong("genreId");
            String genreName = resultSet.getString("genreName");

            var book = new Book(id, title);
            if (authorId != 0 && authorName != null && authorSurname != null) {
                book.author(new Author(authorId, authorName, authorSurname));
            }
            if (genreId != 0 && genreName != null) {
                book.genre(new Genre(genreId, genreName));
            }

            return book;
        }
    }
}
