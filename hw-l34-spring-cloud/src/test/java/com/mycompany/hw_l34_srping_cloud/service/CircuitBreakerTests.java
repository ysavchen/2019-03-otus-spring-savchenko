package com.mycompany.hw_l34_srping_cloud.service;

import com.google.gson.Gson;
import com.mycompany.hw_l34_srping_cloud.configuration.CircuitBreakerProps;
import com.mycompany.hw_l34_srping_cloud.domain.Author;
import com.mycompany.hw_l34_srping_cloud.domain.Book;
import com.mycompany.hw_l34_srping_cloud.domain.Genre;
import com.mycompany.hw_l34_srping_cloud.dto.BookDto;
import com.mycompany.hw_l34_srping_cloud.exception.GetBookException;
import com.mycompany.hw_l34_srping_cloud.repositories.BookRepository;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreaker.State;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CircuitBreakerTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author author = new Author(1, "Philip", "Pratt");
    private final Book guideBook = new Book(1, "A Guide to SQL", author, genre);
    private final BookDto guideBookDto = BookDto.toDto(guideBook);

    private static final String BOOK_SERVICE = "bookService";
    private final Gson gson = new Gson();

    private CircuitBreaker circuitBreaker;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @Autowired
    private CircuitBreakerProps props;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        circuitBreaker = circuitBreakerRegistry.circuitBreaker(BOOK_SERVICE);
        circuitBreaker.transitionToClosedState();
    }

    @Test
    void getById_openCircuitBreaker() {
        when(bookRepository.findById(anyLong())).thenThrow(new GetBookException());

        int firstCall = 1;
        for (int i = firstCall; i < props.getMinimumNumberOfCalls() + 1; i++) {
            try {
                mockMvc.perform(get("/book/{id}", guideBookDto.getId()));
            } catch (Exception ex) {
                //do nothing
            }
        }
        assertThat(circuitBreaker.getState()).isEqualTo(State.OPEN);
    }

    @Test
    void updateTitle_openCircuitBreaker() {
        doThrow(new RuntimeException()).when(bookRepository).updateTitle(anyLong(), anyString());

        var title = "test title";
        int firstCall = 1;
        for (int i = firstCall; i < props.getMinimumNumberOfCalls() + 1; i++) {
            try {
                mockMvc.perform(
                        patch("/api/book/{id}", guideBookDto.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(gson.toJson(guideBookDto.setTitle(title))));
            } catch (Exception ex) {
                //do nothing
            }
        }
        assertThat(circuitBreaker.getState()).isEqualTo(State.OPEN);
    }

    @Test
    void deleteById_openCircuitBreaker() {
        doThrow(new RuntimeException()).when(bookRepository).deleteById(anyLong());

        int firstCall = 1;
        for (int i = firstCall; i < props.getMinimumNumberOfCalls() + 1; i++) {
            try {
                mockMvc.perform(delete("/api/book/{id}", guideBookDto.getId()));
            } catch (Exception ex) {
                //do nothing
            }
        }
        assertThat(circuitBreaker.getState()).isEqualTo(State.OPEN);
    }
}
