package com.mycompany.hw_l17_spring_mvc_modern_apps.controllers;

import com.mycompany.hw_l17_spring_mvc_modern_apps.domain.Author;
import com.mycompany.hw_l17_spring_mvc_modern_apps.domain.Book;
import com.mycompany.hw_l17_spring_mvc_modern_apps.domain.Genre;
import com.mycompany.hw_l17_spring_mvc_modern_apps.dto.BookDto;
import com.mycompany.hw_l17_spring_mvc_modern_apps.service.BookDbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTests {

    private final Genre genre = new Genre(1, "Computers & Technology");
    private final Author prattAuthor = new Author(1, "Philip", "Pratt");
    private final Author learnAuthor = new Author(2, "Michael", "Learn");
    private final Book guideBook = new Book(1, "A Guide to SQL", prattAuthor, genre);
    private final Book conceptsBook = new Book(2, "Concepts of Database Management", prattAuthor, genre);
    private final Book sqlCodingBook = new Book(3, "SQL Programming and Coding", learnAuthor, genre);

    private final BookDto guideBookDto = BookDto.toDto(guideBook);
    private final BookDto conceptsBookDto = BookDto.toDto(conceptsBook);
    private final BookDto sqlCodingBookDto = BookDto.toDto(sqlCodingBook);

    private final List<Book> books = List.of(guideBook, conceptsBook, sqlCodingBook);
    private final List<BookDto> bookDtos = List.of(guideBookDto, conceptsBookDto, sqlCodingBookDto);

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

    @ParameterizedTest
    @ValueSource(strings = {"/book/all", "/"})
    public void getAllBooks(String url) throws Exception {
        when(dbService.getAllBooks()).thenReturn(books);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", is(bookDtos)));
    }

    @Test
    public void deleteBook() throws Exception {
        long id = 5;
        when(dbService.getAllBooks()).thenReturn(books);
        mockMvc.perform(post("/book/delete/{id}", id))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Book with id = " + id + " is deleted"))
                .andExpect(model().attribute("books", is(bookDtos)));
        verify(dbService, atLeastOnce()).deleteById(id);
    }

    @Test
    public void updateTitle() throws Exception {
        var title = "test title";
        when(dbService.getById(guideBook.getId())).thenReturn(Optional.of(guideBook));
        mockMvc.perform(
                post("/book/update/{id}", guideBookDto.getId())
                        .param("title", title))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Title is updated!"))
                .andExpect(model().attribute("book", is(guideBookDto)));
        verify(dbService, atLeastOnce()).updateTitle(guideBook.getId(), title);
    }

    @Test
    public void updateTitle_nonExistingBook() throws Exception {
        var title = "test title";
        when(dbService.getById(NON_EXISTING_ID)).thenReturn(Optional.empty());
        mockMvc.perform(
                post("/book/update/{id}", NON_EXISTING_ID)
                        .param("title", title))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Book with id = " + NON_EXISTING_ID + " is not found"))
                .andExpect(model().attributeDoesNotExist("book"));
    }

    @Test
    public void showAddForm() throws Exception {
        mockMvc.perform(get("/book/new"))
                .andExpect(status().isOk());
    }

    @Test
    public void addBook() throws Exception {
        when(dbService.save(any())).thenReturn(guideBook);
        mockMvc.perform(
                post("/book/new")
                        .param("title", guideBookDto.getTitle())
                        .param("author.name", guideBookDto.getAuthor().getName())
                        .param("author.surname", guideBookDto.getAuthor().getSurname())
                        .param("genre.name", guideBookDto.getGenre().getName()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Book is successfully saved!"))
                .andExpect(model().attribute("book", is(guideBookDto)));
    }

    @Test
    public void addBookNegative() throws Exception {
        when(dbService.save(any())).thenReturn(null);
        mockMvc.perform(
                post("/book/new")
                        .param("title", guideBookDto.getTitle())
                        .param("author.name", guideBookDto.getAuthor().getName())
                        .param("author.surname", guideBookDto.getAuthor().getSurname())
                        .param("genre.name", guideBookDto.getGenre().getName()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Book is not saved!"))
                .andExpect(model().attributeDoesNotExist("book"));
    }
}
