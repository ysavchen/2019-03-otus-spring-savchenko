package com.mycompany.hw_l07_dao_spring_jdbc.shell;

import org.springframework.shell.standard.ShellMethod;

public class BookCommands {

    @ShellMethod(value = "Add book", key = {"ab", "add-book"})
    public String addBook(String title, String authorName,
                          String authorSurname, String genre) {
        return "";
    }

    @ShellMethod(value = "Find book", key = {"fb", "find-book"})
    public String findBook(String title) {
        return "";
    }
}
