package com.mycompany.hw_l07_dao_spring_jdbc.dao;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Genre;
import com.mycompany.hw_l07_dao_spring_jdbc.exception.NoIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(Genre genre) {
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
}
