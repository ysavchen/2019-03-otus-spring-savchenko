package com.mycompany.hw_l16_spring_mvc_view.controllers;

import com.mycompany.hw_l16_spring_mvc_view.dto.AuthorDto;
import com.mycompany.hw_l16_spring_mvc_view.service.AuthorDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private static final String VIEW_AUTHOR_FORM = "authors/viewAuthor";

    private final AuthorDbService dbService;

    @GetMapping("/author/{id}")
    public String getAuthorById(@PathVariable("id") long id, Model model) {
        String message = "Author with id = " + id + " is not found";
        dbService.getById(id).ifPresentOrElse(
                author -> {
                    model.addAttribute("message", "message");
                    model.addAttribute("author", AuthorDto.toDto(author));
                },
                () -> {
                    model.addAttribute("message", message);
                    model.addAttribute("author", null);
                }
        );

        return VIEW_AUTHOR_FORM;
    }
}
