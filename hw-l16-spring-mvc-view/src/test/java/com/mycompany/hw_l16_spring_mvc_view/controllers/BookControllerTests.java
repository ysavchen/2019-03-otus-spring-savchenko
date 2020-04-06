package com.mycompany.hw_l16_spring_mvc_view.controllers;

import com.mycompany.hw_l16_spring_mvc_view.domain.Author;
import com.mycompany.hw_l16_spring_mvc_view.domain.Book;
import com.mycompany.hw_l16_spring_mvc_view.domain.Genre;
import com.mycompany.hw_l16_spring_mvc_view.dto.BookDto;
import com.mycompany.hw_l16_spring_mvc_view.service.BookDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author prattAuthor = new Author(1, "Philip", "Pratt");
    private final Book guideBook = new Book(1, "A Guide to SQL", prattAuthor, genre);
    private final BookDto guideBookDto = BookDto.toDto(guideBook);
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookDbService dbService;

    @Test
    public void getBookById_Found() throws Exception {
        when(dbService.getById(guideBook.getId())).thenReturn(Optional.of(guideBook));
        mockMvc.perform(get("/book/{id}", guideBookDto.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", ""))
                .andExpect(model().attribute("book", hasProperty("id", is(guideBookDto.getId()))))
                .andExpect(model().attribute("book", hasProperty("title", is(guideBookDto.getTitle()))))
                .andExpect(model().attribute("book", hasProperty("author", is(guideBookDto.getAuthor()))))
                .andExpect(model().attribute("book", hasProperty("genre", is(guideBookDto.getGenre()))));
    }

    @Test
    public void getBookById_NotFound() throws Exception {
        when(dbService.getById(NON_EXISTING_ID)).thenReturn(Optional.empty());
        mockMvc.perform(get("/book/{id}", NON_EXISTING_ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", is("Book with id = " + NON_EXISTING_ID + " is not found")))
                .andExpect(model().attributeDoesNotExist("book"));
    }
}
