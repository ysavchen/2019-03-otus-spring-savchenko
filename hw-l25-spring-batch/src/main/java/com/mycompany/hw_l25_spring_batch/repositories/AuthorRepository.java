package com.mycompany.hw_l25_spring_batch.repositories;

import com.mycompany.hw_l25_spring_batch.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
