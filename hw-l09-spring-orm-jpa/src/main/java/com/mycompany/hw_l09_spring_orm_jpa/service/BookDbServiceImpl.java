package com.mycompany.hw_l09_spring_orm_jpa.service;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;
import com.mycompany.hw_l09_spring_orm_jpa.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDbServiceImpl implements BookDbService {

    private final BookRepository bookRepository;

    @Override
    public long insert(Book book) {
        return bookRepository.insert(book);
    }

    public Optional<Book> getById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getBooksByAuthorId(long id) {
        return bookRepository.getBooksByAuthorId(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public boolean update(Book book) {
        if (book.title() != null) {
            return bookRepository.update(book);
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return bookRepository.deleteById(id);
    }
}
