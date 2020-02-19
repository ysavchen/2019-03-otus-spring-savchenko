package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    List<Comment> findByBookId(long id);

    @Query("delete from Comment c where c.book.id = :id and c.content = :#{#comment.content}")
    void deleteCommentByBookId(@Param("id") long id, @Param("comment") Comment comment);
}