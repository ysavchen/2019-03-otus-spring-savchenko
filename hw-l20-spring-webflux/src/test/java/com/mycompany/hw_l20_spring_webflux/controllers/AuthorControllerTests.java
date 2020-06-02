package com.mycompany.hw_l20_spring_webflux.controllers;

import com.mycompany.hw_l20_spring_webflux.domain.Author;
import com.mycompany.hw_l20_spring_webflux.dto.AuthorDto;
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

//    private final Author author = new Author(1, "Philip", "Pratt");
//    private final AuthorDto authorDto = AuthorDto.toDto(author);
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AuthorDbService dbService;
//
//    @Test
//    public void getAuthorById_Found() throws Exception {
//        when(dbService.getById(author.getId())).thenReturn(Optional.of(author));
//        mockMvc.perform(get("/author/{id}", authorDto.getId()))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("author", hasProperty("id", is(authorDto.getId()))))
//                .andExpect(model().attribute("author", hasProperty("name", is(authorDto.getName()))))
//                .andExpect(model().attribute("author", hasProperty("surname", is(authorDto.getSurname()))));
//    }
}
