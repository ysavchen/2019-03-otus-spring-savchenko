package com.mycompany.hw_l11_spring_data_jpa.repositories;

import com.mycompany.hw_l11_spring_data_jpa.domain.Book;
import com.mycompany.hw_l11_spring_data_jpa.domain.Comment;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comment> getCommentsByBookId(long id) {
        return em.createQuery(
                "select c " +
                        "from Comment c " +
                        "join fetch c.book b " +
                        "where b.id = :id", Comment.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
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

    @Override
    public boolean deleteCommentByBookId(long id, @NonNull Comment comment) {
        int rows = em.createQuery("delete from Comment c " +
                "where c.book.id = :id and c.content = :content")
                .setParameter("id", id)
                .setParameter("content", comment.content())
                .executeUpdate();
        return rows > 0;
    }
}
