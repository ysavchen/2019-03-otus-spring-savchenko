package com.mycompany.hw_l13_spring_data_nosql.service;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import com.mycompany.hw_l13_spring_data_nosql.repositories.AuthorRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.BookRepository;
import com.mycompany.hw_l13_spring_data_nosql.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDbServiceImpl implements BookDbService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    public String save(Book book) {
        if (book.getAuthor() != null) {
            authorRepository.save(book.getAuthor());
        }
        return bookRepository.save(book).getId();
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
            Optional<Book> optBook = bookRepository.findById(id);
            if (optBook.isEmpty()) {
                return;
            }
            var book = optBook.get();

            book.setTitle(title);
            bookRepository.save(book);
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

        bookRepository.deleteById(id);
        commentRepository.deleteAllByBookId(id);

        if (author != null) {
            var books = bookRepository.findByAuthorId(author.getId());
            if (books.isEmpty()) {
                authorRepository.deleteById(author.getId());
            }
        }
    }
}
