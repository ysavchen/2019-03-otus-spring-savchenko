package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b " +
            "join fetch b.author a " +
            "join fetch b.genre g " +
            "where a.id = :id") //prevent generating lots of requests to DB
    List<Book> findByAuthorId(@Param("id") long id);

    @Query("select b from Book b " +
            "join fetch b.author a " +
            "join fetch b.genre g " +
            "where g.id = :id")
    List<Book> findByGenreId(@Param("id") long id);

    @Override
    @Query("select b from Book b " +
            "join fetch b.author " +
            "join fetch b.genre")
    List<Book> findAll();

    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    void updateTitle(@Param("id") long id, @Param("title") String title);
}
