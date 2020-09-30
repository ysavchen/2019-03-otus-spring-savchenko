package com.mycompany.hw_l25_spring_batch.service;

import com.mycompany.hw_l25_spring_batch.domain.Comment;

import java.util.List;

public interface CommentDbService {

    List<Comment> getCommentsByBookId(String id);

    String addCommentByBookId(String id, Comment comment);

    void deleteCommentByBookId(String id, Comment comment);
}
