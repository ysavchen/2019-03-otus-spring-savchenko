package com.mycompany.hw_l25_spring_batch.repositories;

import com.mycompany.hw_l25_spring_batch.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByBookId(String id);

    void deleteAllByBookId(String id);
}
