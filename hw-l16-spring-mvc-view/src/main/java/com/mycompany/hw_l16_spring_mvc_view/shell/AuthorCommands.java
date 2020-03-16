package com.mycompany.hw_l16_spring_mvc_view.shell;

import com.mycompany.hw_l16_spring_mvc_view.domain.Author;
import com.mycompany.hw_l16_spring_mvc_view.service.AuthorDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorDbService dbService;

    @ShellMethod(value = "Add author", key = {"aa", "add-author"})
    public String addAuthor(String name, String surname) {
        long id = dbService.save(new Author(name, surname));
        return "Added author with id = " + id;
    }

    @ShellMethod(value = "Find author by id", key = {"fai", "find-author-by-id"})
    public String findAuthorById(long id) {
        var optAuthor = dbService.getById(id);
        if (optAuthor.isEmpty()) {
            return "Author with id = " + id + " is not found";
        }

        var author = optAuthor.get();
        return "Author: " + author.getName() + " " + author.getSurname();
    }
}
