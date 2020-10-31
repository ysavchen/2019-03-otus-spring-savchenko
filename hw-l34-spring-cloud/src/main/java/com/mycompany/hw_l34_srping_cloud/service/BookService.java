package com.mycompany.hw_l34_srping_cloud.service;

import com.mycompany.hw_l34_srping_cloud.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookDto save(BookDto bookDto);

    Optional<BookDto> getById(long id);

    List<BookDto> getAllBooks();

    void updateTitle(long id, String title);

    void deleteById(long id);
}
