package com.mycompany.hw_l11_spring_data_jpa.service;

import com.mycompany.hw_l11_spring_data_jpa.domain.Comment;

import java.util.List;

public interface CommentDbService {

    List<Comment> getCommentsByBookId(long id);

    long addCommentByBookId(long id, Comment comment);

    void deleteCommentByBookId(long id, Comment comment);
}
