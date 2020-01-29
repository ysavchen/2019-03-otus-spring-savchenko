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
        if (book.genre() != null) {
            long genreId = genreDao.insert(book.genre());
            book.genre().setId(genreId);
        }
        if (book.author() != null) {
            long authorId = authorDao.insert(book.author());
            book.author().setId(authorId);
        }

        return bookDao.insert(book);
    }

    public Optional<Book> getById(long id) {
        return bookDao.getById(id);
    }

    public boolean update(Book book) {
        if (book.title() != null) {
            return bookDao.update(book);
        }
        return false;
    }

    public boolean deleteById(long id) {
        return bookDao.deleteById(id);
    }
}
