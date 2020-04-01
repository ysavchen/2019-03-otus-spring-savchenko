package com.mycompany.hw_l16_spring_mvc_view.controllers;

import com.mycompany.hw_l16_spring_mvc_view.domain.Author;
import com.mycompany.hw_l16_spring_mvc_view.dto.AuthorDto;
import com.mycompany.hw_l16_spring_mvc_view.service.AuthorDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private static final String ADD_AUTHOR_FORM = "authors/addAuthor";
    private static final String VIEW_AUTHOR_FORM = "authors/viewAuthor";

    private final AuthorDbService dbService;

    @PostMapping("/author/new")
    public String addAuthor(@RequestBody AuthorDto authorDto, Model model) {
        long id = dbService.save(new Author(authorDto.getName(), authorDto.getSurname()));
        model.addAttribute("message", "Added author with id = " + id);
        return ADD_AUTHOR_FORM;
    }

    @GetMapping("/author/{id}")
    public String getAuthorById(@PathVariable("id") long id, Model model) {
        var optAuthor = dbService.getById(id);
        if (optAuthor.isEmpty()) {
            return "Author with id = " + id + " is not found";
        }

        model.addAttribute("author", AuthorDto.toDto(optAuthor.get()));
        return VIEW_AUTHOR_FORM;
    }
}
