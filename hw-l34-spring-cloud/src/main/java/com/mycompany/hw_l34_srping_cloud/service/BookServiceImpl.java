package com.mycompany.hw_l34_srping_cloud.service;

import com.mycompany.hw_l34_srping_cloud.domain.Book;
import com.mycompany.hw_l34_srping_cloud.dto.BookDto;
import com.mycompany.hw_l34_srping_cloud.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public BookDto save(BookDto bookDto) {
        Book book = bookRepository.save(BookDto.toDomainObject(bookDto));
        return BookDto.toDto(book);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookDto> getById(long id) {
        return bookRepository.findById(id)
                .map(BookDto::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDto::toDto)
                .collect(toList());
    }

    @Transactional
    @Override
    public void updateTitle(long id, String title) {
        if (title != null) {
            bookRepository.updateTitle(id, title);
        }
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
