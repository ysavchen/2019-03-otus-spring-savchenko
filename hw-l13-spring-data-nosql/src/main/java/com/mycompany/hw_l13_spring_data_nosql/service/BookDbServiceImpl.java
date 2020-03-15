package com.mycompany.hw_l13_spring_data_nosql.service;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import com.mycompany.hw_l13_spring_data_nosql.repositories.AuthorRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.BookRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.CommentRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDbServiceImpl implements BookDbService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @Override
    public String save(Book book) {
        return bookRepository.saveWithAuthor(book).getId();
    }

    @Override
    public Optional<Book> getById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getBooksByAuthorId(String id) {
        return bookRepository.findByAuthorId(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void updateTitle(String id, String title) {
        if (title != null) {
            bookRepository.updateTitle(id, title);
        }
    }

    @Override
    public void deleteById(String id) {
        Optional<Book> optBook = bookRepository.findById(id);
        if (optBook.isEmpty()) {
            return;
        }
        var book = optBook.get();
        var author = book.getAuthor();
        var genre = book.getGenre();

        bookRepository.deleteById(id);
        commentRepository.deleteAllByBookId(id);

        //remove relations as CascadeType.REMOVE deletes data used by other books
        if (author != null) {
            var books = bookRepository.findByAuthorId(author.getId());
            if (books.isEmpty()) {
                authorRepository.deleteById(author.getId());
            }
        }

        if (genre != null) {
            var books = bookRepository.findByGenreId(genre.getId());
            if (books.isEmpty()) {
                genreRepository.deleteById(genre.getId());
            }
        }
    }
}
