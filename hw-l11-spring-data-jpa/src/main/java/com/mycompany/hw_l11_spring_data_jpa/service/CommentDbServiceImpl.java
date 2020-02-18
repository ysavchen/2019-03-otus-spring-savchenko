package com.mycompany.hw_l11_spring_data_jpa.service;

import com.mycompany.hw_l11_spring_data_jpa.domain.Comment;
import com.mycompany.hw_l11_spring_data_jpa.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentDbServiceImpl implements CommentDbService {

    private final CommentRepository repository;

    @Transactional(readOnly = true)
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
