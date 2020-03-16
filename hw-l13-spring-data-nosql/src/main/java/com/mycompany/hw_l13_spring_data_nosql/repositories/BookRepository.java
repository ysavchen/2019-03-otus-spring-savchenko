package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByAuthorId(String id);

}
