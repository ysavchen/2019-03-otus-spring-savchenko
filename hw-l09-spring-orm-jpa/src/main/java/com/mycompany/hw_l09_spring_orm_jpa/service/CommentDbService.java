package com.mycompany.hw_l09_spring_orm_jpa.service;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Comment;

import java.util.List;

public interface CommentDbService {

    List<Comment> getCommentsByBookId(long id);

    boolean addCommentByBookId(long id, String comment);

    boolean deleteCommentByBookId(long id, String comment);
}
