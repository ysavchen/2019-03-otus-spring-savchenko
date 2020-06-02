package com.mycompany.hw_l20_spring_webflux.controllers;

import com.mycompany.hw_l20_spring_webflux.dto.AuthorDto;
import com.mycompany.hw_l20_spring_webflux.exceptions.EntityNotFoundException;
import com.mycompany.hw_l20_spring_webflux.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private static final String VIEW_AUTHOR_FORM = "/html/authors/viewAuthor";

    private final AuthorRepository authorRepository;

    //todo: remove as DBRef not supported for reactive
    @GetMapping("/author/{id}")
    public Mono<String> getAuthorById(@PathVariable("id") String id, Model model) {
        return authorRepository.findById(id).map(
                author -> {
                    model.addAttribute("author", AuthorDto.toDto(author));
                    return VIEW_AUTHOR_FORM;
                }).onErrorMap(error -> new EntityNotFoundException("Author with id = " + id + " is not found"));
    }
}
