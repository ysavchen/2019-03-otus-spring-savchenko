package com.mycompany.hw_l09_spring_orm_jpa.service;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Comment;
import com.mycompany.hw_l09_spring_orm_jpa.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentDbServiceImpl implements CommentDbService {

    private final CommentRepository repository;

    @Override
    public List<Comment> getCommentsByBookId(long id) {
        return repository.getCommentsByBookId(id);
    }

    @Override
    public long addCommentByBookId(long id, Comment comment) {
        return repository.addCommentByBookId(id, comment);
    }

    @Override
    public boolean deleteCommentByBookId(long id, Comment comment) {
        return repository.deleteCommentByBookId(id, comment);
    }
}
