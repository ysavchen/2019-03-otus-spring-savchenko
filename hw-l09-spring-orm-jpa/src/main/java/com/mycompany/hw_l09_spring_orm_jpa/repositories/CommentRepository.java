package com.mycompany.hw_l09_spring_orm_jpa.repositories;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> getCommentsByBookId(long id);

    boolean addCommentByBookId(long id, String comment);

    boolean deleteCommentByBookId(long id, String comment);
}
