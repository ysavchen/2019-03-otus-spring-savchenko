package com.mycompany.hw_l07_dao_spring_jdbc.service;

import com.mycompany.hw_l07_dao_spring_jdbc.dao.AuthorDao;
import com.mycompany.hw_l07_dao_spring_jdbc.dao.BookDao;
import com.mycompany.hw_l07_dao_spring_jdbc.dao.GenreDao;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDbServiceImpl implements BookDbService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public long insert(Book book) {
        if (book.getGenre() != null) {
            long genreId = genreDao.insert(book.getGenre());
            book.getGenre().setId(genreId);
        }
        if (book.getAuthor() != null) {
            long authorId = authorDao.insert(book.getAuthor());
            book.getAuthor().setId(authorId);
        }

        return bookDao.insert(book);
    }

    public Optional<Book> getById(long id) {
        return bookDao.getById(id);
    }

    public void update(Book book) {
        if (book.getTitle() != null) bookDao.update(book);
    }

    public void deleteById(long id) {
        bookDao.deleteById(id);
    }
}
