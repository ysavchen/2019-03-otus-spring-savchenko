package com.mycompany.hw_l16_spring_mvc_view.service;

import com.mycompany.hw_l16_spring_mvc_view.domain.Comment;

import java.util.List;

public interface CommentDbService {

    List<Comment> getCommentsByBookId(long id);

    long addCommentByBookId(long id, Comment comment);

    void deleteCommentByBookId(long id, Comment comment);
}
