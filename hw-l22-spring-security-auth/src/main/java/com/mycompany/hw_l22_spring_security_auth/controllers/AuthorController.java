package com.mycompany.hw_l22_spring_security_auth.controllers;

import com.mycompany.hw_l22_spring_security_auth.dto.AuthorDto;
import com.mycompany.hw_l22_spring_security_auth.service.AuthorDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private static final String VIEW_AUTHOR_FORM = "authors/viewAuthor";

    private final AuthorDbService dbService;

    @GetMapping("/author/{id}")
    public String getAuthorById(@PathVariable("id") long id, Model model) {
        dbService.getById(id).ifPresentOrElse(
                author -> {
                    model.addAttribute("message", "");
                    model.addAttribute("author", AuthorDto.toDto(author));
                },
                () -> {
                    var message = "Author with id = " + id + " is not found";
                    model.addAttribute("message", message);
                    model.addAttribute("author", null);
                }
        );

        return VIEW_AUTHOR_FORM;
    }
}
