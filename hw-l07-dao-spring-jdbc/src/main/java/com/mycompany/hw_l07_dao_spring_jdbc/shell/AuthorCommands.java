package com.mycompany.hw_l07_dao_spring_jdbc.shell;

import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;
import com.mycompany.hw_l07_dao_spring_jdbc.service.AuthorDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static java.util.stream.Collectors.joining;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {

    private final AuthorDbService dbService;

    @ShellMethod(value = "Find author by id", key = {"fai", "find-author-by-id"})
    public String findAuthorById(long id) {
        var optAuthor = dbService.getById(id);
        if (optAuthor.isEmpty()) {
            return "Author with id = " + id + " is not found";
        }

        var author = optAuthor.get();
        String books = author.getBooks().stream()
                .map(Book::getTitle)
                .collect(joining(", "));

        return "Author: " + author.getName() + " " + author.getSurname() + "\n" +
                "Books: " + books;
    }
}
