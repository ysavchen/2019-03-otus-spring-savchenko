package com.mycompany.hw_l16_spring_mvc_view.controllers;

import com.mycompany.hw_l16_spring_mvc_view.dto.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BookController {

    @PostMapping("/book")
    public String addBook(@RequestBody BookDto bookDto, Model model) {
        return "";
    }

    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable("id") long id, Model model) {
        return "";
    }
}
