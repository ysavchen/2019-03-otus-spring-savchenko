package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBookId(long id);

    @Modifying
    @Query("delete from Comment c where c.book.id = :id and c.content = :#{#comment.content}")
    void deleteByBookId(@Param("id") long id, @Param("comment") Comment comment);

    @Modifying
    @Query("delete from Comment c where c.book.id = :id")
    void deleteAllByBookId(@Param("id") long id);
}
