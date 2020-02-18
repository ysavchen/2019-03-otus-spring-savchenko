package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Author;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long insert(@NonNull Author author) {
        if (author.id() <= 0) {
            em.persist(author);
        } else {
            em.merge(author);
        }
        return author.id();
    }

    @Override
    public Optional<Author> getById(long id) {
        try {
            var author = em.find(Author.class, id);
            return Optional.ofNullable(author);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
