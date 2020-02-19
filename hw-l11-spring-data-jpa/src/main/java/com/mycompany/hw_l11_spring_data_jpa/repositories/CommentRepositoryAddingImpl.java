package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Book;
import com.mycompany.hw_l11_spring_data_jpa.domain.Comment;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CommentRepositoryAddingImpl implements CommentRepositoryAdding {

    @PersistenceContext
    private EntityManager em;

    public long addCommentByBookId(long id, @NonNull Comment comment) {
        var book = em.find(Book.class, id);
        if (book != null) {
            comment.book(book);
            if (comment.id() <= 0) {
                em.persist(comment);
            } else {
                em.merge(comment);
            }
            return comment.id();
        }
        return 0;
    }
}
