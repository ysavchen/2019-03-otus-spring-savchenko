package com.mycompany.hw_l11_spring_data_jpa.shell;

import com.mycompany.hw_l11_spring_data_jpa.domain.Author;
import com.mycompany.hw_l11_spring_data_jpa.service.AuthorDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorDbService dbService;

    @ShellMethod(value = "Add author", key = {"aa", "add-author"})
    public String addBook(String name, String surname) {
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
        return "Author: " + author.name() + " " + author.surname();
    }
}
