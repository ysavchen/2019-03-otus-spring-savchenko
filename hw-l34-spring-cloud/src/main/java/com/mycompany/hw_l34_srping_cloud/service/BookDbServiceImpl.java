package com.mycompany.hw_l34_srping_cloud.service;

import com.mycompany.hw_l34_srping_cloud.domain.Author;
import com.mycompany.hw_l34_srping_cloud.domain.Book;
import com.mycompany.hw_l34_srping_cloud.domain.Genre;
import com.mycompany.hw_l34_srping_cloud.repositories.BookRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(
            groupKey = "library",
            fallbackMethod = "getBookForNonAvailable"
    )
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @HystrixCommand(
            groupKey = "library",
            fallbackMethod = "getBookForNonAvailable"
    )
    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getById(long id) {
        return bookRepository.findById(id);
    }

    @HystrixCommand(
            groupKey = "library",
            fallbackMethod = "getEmptyBookList"
    )
    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @HystrixCommand(groupKey = "library")
    @Override
    public void updateTitle(long id, String title) {
        if (title != null) {
            bookRepository.updateTitle(id, title);
        }
    }

    @HystrixCommand(groupKey = "library")
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> getBookForNonAvailable() {
        return Optional.of(
                new Book(0L, "N/A",
                        new Author(0L, "N/A", "N/A"),
                        new Genre(0L, "N/A")
                )
        );
    }

    public List<Book> getEmptyBookList() {
        return List.of();
    }
}
