package com.mycompany.hw_l16_spring_mvc_view.controllers;

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

    private final AuthorDbService dbService;

    @PostMapping("/author")
    public String addAuthor(@RequestBody AuthorDto authorDto, Model model) {
        return "";
    }

    @GetMapping("/author/{id}")
    public String getAuthorById(@PathVariable("id") long id, Model model) {
        return "";
    }
}
