package com.mycompany.hw_l34_srping_cloud.controllers;

import com.google.gson.Gson;
import com.mycompany.hw_l34_srping_cloud.domain.Author;
import com.mycompany.hw_l34_srping_cloud.domain.Book;
import com.mycompany.hw_l34_srping_cloud.domain.Genre;
import com.mycompany.hw_l34_srping_cloud.dto.BookDto;
import com.mycompany.hw_l34_srping_cloud.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
public class BookRestControllerTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author prattAuthor = new Author(1, "Philip", "Pratt");
    private final Author learnAuthor = new Author(2, "Michael", "Learn");
    private final BookDto guideBookDto = BookDto.toDto(new Book(1, "A Guide to SQL", prattAuthor, genre));
    private final BookDto conceptsBookDto = BookDto.toDto(new Book(2, "Concepts of Database Management", prattAuthor, genre));
    private final BookDto sqlCodingBookDto = BookDto.toDto(new Book(3, "SQL Programming and Coding", learnAuthor, genre));

    private final List<BookDto> booksDto = List.of(guideBookDto, conceptsBookDto, sqlCodingBookDto);

    private final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void getAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(booksDto);
        mockMvc.perform(get("/api/book"))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(booksDto)));
    }

    @Test
    public void updateTitle() throws Exception {
        when(bookService.getById(anyLong())).thenReturn(Optional.of(guideBookDto));

        var title = "test title";
        mockMvc.perform(
                patch("/api/book/{id}", guideBookDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(guideBookDto.setTitle(title))))
                .andExpect(status().isOk());
        verify(bookService, atLeastOnce()).updateTitle(guideBookDto.getId(), title);
    }

    @Test
    public void deleteBook() throws Exception {
        long id = 5;
        when(bookService.getAllBooks()).thenReturn(booksDto);
        mockMvc.perform(delete("/api/book/{id}", id))
                .andExpect(status().isOk());
        verify(bookService, times(1)).deleteById(id);
    }

    @Test
    public void addBook() throws Exception {
        when(bookService.save(any())).thenReturn(guideBookDto);
        mockMvc.perform(
                post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(guideBookDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(guideBookDto)));
    }
}
