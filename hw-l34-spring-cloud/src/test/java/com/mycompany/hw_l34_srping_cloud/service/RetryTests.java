package com.mycompany.hw_l34_srping_cloud.service;

import com.google.gson.Gson;
import com.mycompany.hw_l34_srping_cloud.domain.Author;
import com.mycompany.hw_l34_srping_cloud.domain.Book;
import com.mycompany.hw_l34_srping_cloud.domain.Genre;
import com.mycompany.hw_l34_srping_cloud.dto.BookDto;
import com.mycompany.hw_l34_srping_cloud.repositories.BookRepository;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class RetryTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author author = new Author(1, "Philip", "Pratt");
    private final Book guideBook = new Book(1, "A Guide to SQL", author, genre);
    private final BookDto guideBookDto = BookDto.toDto(guideBook);

    private static final String BOOK_SERVICE = "bookService";
    private final Gson gson = new Gson();

    @Autowired
    protected RetryRegistry retryRegistry;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    private Retry.Metrics metrics;

    @BeforeEach
    public void setup() {
        metrics = retryRegistry.retry(BOOK_SERVICE).getMetrics();
    }

    @Test
    void saveWithRetry() throws Exception {
        when(bookRepository.save(any())).thenReturn(guideBook);

        for (int i = 0; i < 2; i++) {
            mockMvc.perform(
                    post("/api/book")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(gson.toJson(guideBookDto)));
        }

        long successCallsWithoutRetry = metrics.getNumberOfSuccessfulCallsWithoutRetryAttempt();
        long failedCallsWithRetry = metrics.getNumberOfFailedCallsWithRetryAttempt();
        long successCallsWithRetry = metrics.getNumberOfSuccessfulCallsWithRetryAttempt();

        assertEquals(1, successCallsWithoutRetry);
        assertEquals(0, failedCallsWithRetry);
        assertEquals(1, successCallsWithRetry);
    }
}
