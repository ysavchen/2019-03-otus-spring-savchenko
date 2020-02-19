package com.mycompany.hw_l11_spring_data_jpa.service;

import com.mycompany.hw_l11_spring_data_jpa.domain.Book;
import com.mycompany.hw_l11_spring_data_jpa.repositories.BookRepository;
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
    public long save(Book book) {
        return repository.save(book).id();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getById(long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksByAuthorId(long id) {
        return repository.findByAuthorId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public void updateTitle(long id, String title) {
        if (title != null) {
            repository.updateTitle(id, title);
        }
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
