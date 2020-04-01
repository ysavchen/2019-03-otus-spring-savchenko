package com.mycompany.hw_l16_spring_mvc_view.service;

import com.mycompany.hw_l16_spring_mvc_view.domain.Book;
import com.mycompany.hw_l16_spring_mvc_view.repositories.BookRepository;
import com.mycompany.hw_l16_spring_mvc_view.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookDbServiceImpl implements BookDbService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    public long save(Book book) {
        return bookRepository.save(book).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksByAuthorId(long id) {
        return bookRepository.findByAuthorId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void updateTitle(long id, String title) {
        if (title != null) {
            bookRepository.updateTitle(id, title);
        }
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
        commentRepository.deleteAllByBookId(id);
    }
}
