package com.mycompany.hw_l13_spring_data_nosql.service;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import com.mycompany.hw_l13_spring_data_nosql.domain.Comment;
import com.mycompany.hw_l13_spring_data_nosql.repositories.BookRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        commentRepository.deleteByBookId(id, comment);
    }
}
