package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByBookId(String id);

    //    @Modifying
//    @Query("delete from Comment c where c.book.id = :id and c.content = :#{#comment.content}")
    void deleteByBookId(@Param("id") String id, @Param("comment") Comment comment);

    //    @Modifying
//    @Query("delete from Comment c where c.book.id = :id")
    void deleteAllByBookId(@Param("id") String id);
}
