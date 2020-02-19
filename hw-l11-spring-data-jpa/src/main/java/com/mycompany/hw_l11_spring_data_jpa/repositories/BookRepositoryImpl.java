package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Book;
import lombok.NonNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class BookRepositoryImpl {

    private EntityManager em;

    public long insert(@NonNull Book book) {
        if (book.id() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
        return book.id();
    }

    public Optional<Book> getById(long id) {
        try {
            var book = em.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<Book> getBooksByAuthorId(long id) {
        return em.createQuery(
                "select b " +
                        "from Book b " +
                        "join fetch b.author a " +
                        "join fetch b.genre g " +
                        "where a.id = :id", Book.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Book> getAllBooks() {
        return em.createQuery(
                "select b from Book b " +
                        "join fetch b.author " +
                        "join fetch b.genre", Book.class)
                .getResultList();
    }

    public boolean update(@NonNull Book book) {
        int rows = em.createQuery("update Book b set b.title = :title where b.id = :id")
                .setParameter("title", book.title())
                .setParameter("id", book.id())
                .executeUpdate();
        return rows > 0;
    }

    public boolean deleteById(long id) {
        int rows = em.createQuery("delete from Book b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return rows > 0;
    }
}
