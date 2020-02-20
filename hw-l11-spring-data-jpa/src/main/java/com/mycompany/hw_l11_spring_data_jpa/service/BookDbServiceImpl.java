package com.mycompany.hw_l11_spring_data_jpa.service;

import com.mycompany.hw_l11_spring_data_jpa.domain.Book;
import com.mycompany.hw_l11_spring_data_jpa.repositories.AuthorRepository;
import com.mycompany.hw_l11_spring_data_jpa.repositories.BookRepository;
import com.mycompany.hw_l11_spring_data_jpa.repositories.CommentRepository;
import com.mycompany.hw_l11_spring_data_jpa.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookDbServiceImpl implements BookDbService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @Override
    public long save(Book book) {
        return bookRepository.save(book).id();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksByAuthorId(long id) {
        return bookRepository.findByAuthorId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void updateTitle(long id, String title) {
        if (title != null) {
            bookRepository.updateTitle(id, title);
        }
    }

    @Override
    public void deleteById(long id) {
        Optional<Book> optBook = bookRepository.findById(id);
        if (optBook.isEmpty()) {
            return;
        }
        var book = optBook.get();
        var author = book.author();
        var genre = book.genre();

        bookRepository.deleteById(id);
        if (author != null) {
            var books = bookRepository.findByAuthorId(author.id());
            if (books.isEmpty()) {
                authorRepository.deleteById(author.id());
            }
        }

        if (genre != null) {
            var books = bookRepository.findByGenreId(genre.id());
            if (books.isEmpty()) {
                genreRepository.deleteById(genre.id());
            }
        }

        var comments = commentRepository.findByBookId(id);
        if (!comments.isEmpty()) {
            commentRepository.deleteAllByBookId(id);
        }
    }
}
