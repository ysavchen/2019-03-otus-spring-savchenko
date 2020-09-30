package com.mycompany.hw_l25_spring_batch.service;

import com.mycompany.hw_l25_spring_batch.domain.Book;
import com.mycompany.hw_l25_spring_batch.repositories.AuthorRepository;
import com.mycompany.hw_l25_spring_batch.repositories.BookRepository;
import com.mycompany.hw_l25_spring_batch.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDbServiceImpl implements BookDbService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    public String save(Book book) {
        if (book.getAuthor() != null) {
            authorRepository.save(book.getAuthor());
        }
        return bookRepository.save(book).getId();
    }

    @Override
    public Optional<Book> getById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getBooksByAuthorId(String id) {
        return bookRepository.findByAuthorId(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void updateTitle(String id, String title) {
        if (title != null) {
            bookRepository.updateTitle(id, title);
        }
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
        commentRepository.deleteAllByBookId(id);
    }
}
