package com.mycompany.hw_l09_spring_orm_jpa.service;

import com.mycompany.hw_l09_spring_orm_jpa.dao.AuthorRepository;
import com.mycompany.hw_l09_spring_orm_jpa.dao.BookRepository;
import com.mycompany.hw_l09_spring_orm_jpa.dao.GenreRepository;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDbServiceImpl implements BookDbService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public long insert(Book book) {
        if (book.genre() != null) {
            long genreId = genreRepository.insert(book.genre());
            book.genre().setId(genreId);
        }
        if (book.author() != null) {
            long authorId = authorRepository.insert(book.author());
            book.author().setId(authorId);
        }

        return bookRepository.insert(book);
    }

    public Optional<Book> getById(long id) {
        return bookRepository.getById(id);
    }

    public boolean update(Book book) {
        if (book.title() != null) {
            return bookRepository.update(book);
        }
        return false;
    }

    public boolean deleteById(long id) {
        return bookRepository.deleteById(id);
    }
}
