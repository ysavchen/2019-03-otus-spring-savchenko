package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    List<Book> findByAuthorId(@Param("id") String id);

    //    @Query("select b from Book b " +
//            "join fetch b.author a " +
//            "join fetch b.genre g " +
//            "where g.id = :id")
    List<Book> findByGenreId(@Param("id") String id);

//    @Override
//    @Query("select b from Book b " +
//            "join fetch b.author " +
//            "join fetch b.genre")
//    List<Book> findAll();

    //    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    void updateTitle(@Param("id") String id, @Param("title") String title);
}
