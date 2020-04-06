package com.mycompany.hw_l16_spring_mvc_view.controllers;

import com.mycompany.hw_l16_spring_mvc_view.domain.Author;
import com.mycompany.hw_l16_spring_mvc_view.service.AuthorDbService;
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

@WebMvcTest(AuthorController.class)
public class AuthorControllerTests {

    private final Author author = new Author(1, "Philip", "Pratt");
    private static final long NON_EXISTING_ID = 50;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorDbService dbService;

    @Test
    public void getAuthorById_Found() throws Exception {
        when(dbService.getById(author.getId())).thenReturn(Optional.of(author));
        mockMvc.perform(get("/author/{id}", author.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", ""))
                .andExpect(model().attribute("author", hasProperty("id", is(author.getId()))))
                .andExpect(model().attribute("author", hasProperty("name", is(author.getName()))))
                .andExpect(model().attribute("author", hasProperty("surname", is(author.getSurname()))));
    }

    @Test
    public void getAuthorById_NotFound() throws Exception {
        when(dbService.getById(NON_EXISTING_ID)).thenReturn(Optional.empty());
        mockMvc.perform(get("/author/{id}", NON_EXISTING_ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", is("Author with id = " + NON_EXISTING_ID + " is not found")))
                .andExpect(model().attributeDoesNotExist("author"));
    }
}
