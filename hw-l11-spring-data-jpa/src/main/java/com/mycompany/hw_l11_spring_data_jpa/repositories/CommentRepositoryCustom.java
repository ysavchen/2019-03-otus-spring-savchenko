package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Comment;

public interface CommentRepositoryCustom {

    long addCommentByBookId(long id, Comment comment);
}
