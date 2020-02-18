package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Genre;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long insert(@NonNull Genre genre) {
        if (genre.id() <= 0) {
            em.persist(genre);
        } else {
            em.merge(genre);
        }
        return genre.id();
    }

    @Override
    public Optional<Genre> getById(long id) {
        try {
            var genre = em.find(Genre.class, id);
            return Optional.ofNullable(genre);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }
}
