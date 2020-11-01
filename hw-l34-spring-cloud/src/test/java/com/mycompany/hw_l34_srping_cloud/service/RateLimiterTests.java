package com.mycompany.hw_l34_srping_cloud.service;

import com.mycompany.hw_l34_srping_cloud.configuration.RateLimiterProps;
import com.mycompany.hw_l34_srping_cloud.domain.Author;
import com.mycompany.hw_l34_srping_cloud.domain.Book;
import com.mycompany.hw_l34_srping_cloud.domain.Genre;
import com.mycompany.hw_l34_srping_cloud.repositories.BookRepository;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class RateLimiterTests {

    private static final String BOOK_SERVICE = "bookService";

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author prattAuthor = new Author(1, "Philip", "Pratt");
    private final Author learnAuthor = new Author(2, "Michael", "Learn");
    private final Book guideBook = new Book(1, "A Guide to SQL", prattAuthor, genre);
    private final Book conceptsBook = new Book(2, "Concepts of Database Management", prattAuthor, genre);
    private final Book sqlCodingBook = new Book(3, "SQL Programming and Coding", learnAuthor, genre);

    private final List<Book> books = List.of(guideBook, conceptsBook, sqlCodingBook);

    @Autowired
    private RateLimiterRegistry rateLimiterRegistry;

    @Autowired
    private RateLimiterProps props;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    private RateLimiter rateLimiter;

    @BeforeEach
    public void setup() {
        rateLimiter = rateLimiterRegistry.rateLimiter(BOOK_SERVICE);
    }

    @Test
    void getAllBooksWithExceededRateLimiter() throws Exception {
        when(bookRepository.findAll()).thenReturn(books);

        int firstCall = 1;
        for (int i = firstCall; i < props.getLimitForPeriod() + 1; i++) {
            mockMvc.perform(get("/api/book"));
        }

        int availablePermissions = rateLimiter.getMetrics().getAvailablePermissions();
        assertEquals(0, availablePermissions);
    }
}
