package com.mycompany.hw_l07_dao_spring_jdbc.dao;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Genre;
import com.mycompany.hw_l07_dao_spring_jdbc.exception.NoIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        var params = new MapSqlParameterSource()
                .addValue("title", book.getTitle());

        jdbc.update("insert into books (title) values (:title)", params, keyHolder);

        var number = keyHolder.getKey();
        if (number == null) {
            throw new NoIdException("No id for book with title = " + book.getTitle());
        }
        return number.longValue();
    }

    @Override
    public void updateWithAuthorRelation(long bookId, long authorId) {

    }

    @Override
    public void updateWithGenreRelation(long bookId, long genreId) {

    }

    @Override
    public Optional<Book> getById(long id) {
        Map<String, Object> params = Map.of("id", id);
        try {
            var book = jdbc.queryForObject(
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
    public void update(Book book) {

    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Map.of("id", id);
        jdbc.update("delete from books where id = :id", params);
    }

    @Override
    public List<Book> getAll() {
        return null;
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
            return new Book(
                    id, title,
                    new Author(authorId, authorName, authorSurname),
                    new Genre(genreId, genreName)
            );
        }
    }
}
