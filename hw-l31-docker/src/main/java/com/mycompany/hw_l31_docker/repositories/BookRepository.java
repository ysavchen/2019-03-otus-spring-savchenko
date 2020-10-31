package com.mycompany.hw_l31_docker.repositories;

import com.mycompany.hw_l31_docker.domain.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph("book-entity-graph")
    List<Book> findAll();

    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    void updateTitle(@Param("id") long id, @Param("title") String title);
}
