package com.mycompany.hw_l09_spring_orm_jpa.repositories;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comment> getCommentsByBookId(long id) {
        return null;
    }

    @Override
    public boolean addCommentByBookId(long id, String comment) {
        return false;
    }

    @Override
    public boolean deleteCommentByBookId(long id, String comment) {
        return false;
    }
}
