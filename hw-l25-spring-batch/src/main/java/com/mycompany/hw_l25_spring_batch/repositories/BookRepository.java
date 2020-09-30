package com.mycompany.hw_l25_spring_batch.repositories;

import com.mycompany.hw_l25_spring_batch.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    List<Book> findByAuthorId(String id);

}
