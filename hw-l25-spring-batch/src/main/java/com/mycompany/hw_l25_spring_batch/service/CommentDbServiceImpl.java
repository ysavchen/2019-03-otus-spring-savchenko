package com.mycompany.hw_l25_spring_batch.service;

import com.mycompany.hw_l25_spring_batch.domain.Book;
import com.mycompany.hw_l25_spring_batch.domain.Comment;
import com.mycompany.hw_l25_spring_batch.repositories.BookRepository;
import com.mycompany.hw_l25_spring_batch.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentDbServiceImpl implements CommentDbService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByBookId(String id) {
        return commentRepository.findByBookId(id);
    }

    @Override
    public String addCommentByBookId(String id, Comment comment) {
        Optional<Book> optBook = bookRepository.findById(id);
        if (optBook.isPresent()) {
            comment.setBook(optBook.get());
            return commentRepository.save(comment).getId();
        }
        return "";
    }

    @Override
    public void deleteCommentByBookId(String id, Comment comment) {
        var comments = commentRepository.findByBookId(id);
        if (comments.isEmpty()) {
            return;
        }

        comments.stream()
                .filter(comm -> Objects.equals(comment.getContent(), comm.getContent()))
                .forEach(commentRepository::delete);
    }
}
