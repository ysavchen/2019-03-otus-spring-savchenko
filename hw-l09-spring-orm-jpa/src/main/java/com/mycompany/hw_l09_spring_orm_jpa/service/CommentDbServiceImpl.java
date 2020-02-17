package com.mycompany.hw_l09_spring_orm_jpa.service;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Comment;
import com.mycompany.hw_l09_spring_orm_jpa.repositories.BookRepository;
import com.mycompany.hw_l09_spring_orm_jpa.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentDbServiceImpl implements CommentDbService {

    private final CommentRepository repository;

    @Override
    public List<Comment> getCommentsByBookId(long id) {
        return repository.getCommentsByBookId(id);
    }

    @Override
    public boolean addCommentByBookId(long id, String comment) {
        return repository.addCommentByBookId(id, comment);
    }

    @Override
    public boolean deleteCommentByBookId(long id, String comment) {
        return repository.deleteCommentByBookId(id, comment);
    }
}
