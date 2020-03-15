package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByAuthorId(@Param("id") String id);

    //    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    void updateTitle(@Param("id") String id, @Param("title") String title);
}
