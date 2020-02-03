package com.mycompany.hw_l09_spring_orm_jpa.dao;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Genre;
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
public class GenreRepositoryImpl implements GenreRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(@NonNull Genre genre) {
        var keyHolder = new GeneratedKeyHolder();
        var params = new MapSqlParameterSource()
                .addValue("name", genre.getName());

        jdbc.update("insert into genres (name) values (:name)", params, keyHolder);

        var number = keyHolder.getKey();
        if (number == null) {
            throw new NoIdException("No id for genre with name = " + genre.getName());
        }
        return number.longValue();
    }

    @Override
    public Optional<Genre> getById(long id) {
        Map<String, Object> params = Map.of("id", id);
        try {
            Genre genre = jdbc.queryForObject(
                    "select g.id as genreId, g.name as genreName " +
                            "from genres g " +
                            "where g.id = :id", params, new GenreMapper()
            );
            return Optional.ofNullable(genre);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("genreId");
            String name = resultSet.getString("genreName");
            return new Genre(id, name);
        }
    }
}
