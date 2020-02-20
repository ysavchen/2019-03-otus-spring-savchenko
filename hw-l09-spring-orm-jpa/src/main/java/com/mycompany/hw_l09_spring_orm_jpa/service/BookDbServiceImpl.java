package com.mycompany.hw_l09_spring_orm_jpa.service;

import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;
import com.mycompany.hw_l09_spring_orm_jpa.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookDbServiceImpl implements BookDbService {

    private final BookRepository repository;

    @Override
    public long insert(Book book) {
        return repository.insert(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getById(long id) {
        return repository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksByAuthorId(long id) {
        return repository.getBooksByAuthorId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    @Override
    public boolean update(Book book) {
        if (book.title() != null) {
            return repository.update(book);
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return repository.deleteById(id);
    }
}
