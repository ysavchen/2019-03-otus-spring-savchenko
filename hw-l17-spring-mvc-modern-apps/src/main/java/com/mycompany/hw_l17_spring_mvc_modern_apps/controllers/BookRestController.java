package com.mycompany.hw_l17_spring_mvc_modern_apps.controllers;

import com.mycompany.hw_l17_spring_mvc_modern_apps.dto.BookDto;
import com.mycompany.hw_l17_spring_mvc_modern_apps.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookDbService dbService;

    @GetMapping("/api/book")
    public List<BookDto> getAllBooks() {
        return dbService.getAllBooks()
                .stream()
                .map(BookDto::toDto)
                .collect(toList());
    }
}
