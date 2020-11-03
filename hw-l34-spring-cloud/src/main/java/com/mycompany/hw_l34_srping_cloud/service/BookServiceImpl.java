package com.mycompany.hw_l34_srping_cloud.service;

import com.mycompany.hw_l34_srping_cloud.domain.Book;
import com.mycompany.hw_l34_srping_cloud.dto.AuthorDto;
import com.mycompany.hw_l34_srping_cloud.dto.BookDto;
import com.mycompany.hw_l34_srping_cloud.dto.GenreDto;
import com.mycompany.hw_l34_srping_cloud.exception.GetBookException;
import com.mycompany.hw_l34_srping_cloud.exception.SaveBookException;
import com.mycompany.hw_l34_srping_cloud.repositories.BookRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final String BOOK_SERVICE = "bookService";

    private final BookRepository bookRepository;
    private int saveBookCounter = 0;
    private int getBookCounter = 0;

    @Retry(name = BOOK_SERVICE)
    @Transactional
    @Override
    public BookDto save(BookDto bookDto) {
        saveBookCounter++;
        if (saveBookCounter > 1 && saveBookCounter < 4) {
            System.out.println("Book save is not successful. saveBookCounter: " + saveBookCounter);
            throw new SaveBookException();
        }

        Book book = bookRepository.save(BookDto.toDomainObject(bookDto));
        return BookDto.toDto(book);
    }

    @CircuitBreaker(name = BOOK_SERVICE, fallbackMethod = "getBookForNonAvailable")
    @Transactional(readOnly = true)
    @Override
    public Optional<BookDto> getById(long id) {
        getBookCounter++;
        System.out.println("Executing getById(). getBookCounter: " + getBookCounter);
        if (getBookCounter > 10 && getBookCounter < 16) {
            System.out.println("getById() is not successful. getBookCounter: " + getBookCounter);
            throw new GetBookException();
        }

        return bookRepository.findById(id)
                .map(BookDto::toDto);
    }

    @RateLimiter(name = BOOK_SERVICE)
    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDto::toDto)
                .collect(toList());
    }

    @CircuitBreaker(name = BOOK_SERVICE)
    @Transactional
    @Override
    public void updateTitle(long id, String title) {
        if (title != null) {
            bookRepository.updateTitle(id, title);
        }
    }

    @CircuitBreaker(name = BOOK_SERVICE)
    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    public Optional<BookDto> getBookForNonAvailable(long id, Throwable ex) {
        System.out.println("Executing getBookForNonAvailable()");
        return Optional.of(
                new BookDto(id, "N/A",
                        new AuthorDto(0L, "N/A", "N/A"),
                        new GenreDto(0L, "N/A")
                )
        );
    }
}
