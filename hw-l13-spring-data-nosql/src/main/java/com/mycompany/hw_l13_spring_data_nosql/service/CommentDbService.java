package com.mycompany.hw_l13_spring_data_nosql.service;

import com.mycompany.hw_l13_spring_data_nosql.domain.Comment;

import java.util.List;

public interface CommentDbService {

    List<Comment> getCommentsByBookId(String id);

    String addCommentByBookId(String id, Comment comment);

    void deleteCommentByBookId(String id, Comment comment);
}
