package com.mycompany.hw_l07_dao_spring_jdbc.service;

import com.mycompany.hw_l07_dao_spring_jdbc.dao.AuthorDao;
import com.mycompany.hw_l07_dao_spring_jdbc.dao.BookDao;
import com.mycompany.hw_l07_dao_spring_jdbc.dao.GenreDao;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookDbServiceImpl implements BookDbService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public long insert(Book book) {
        long genreId = genreDao.insert(book.getGenre());
        long authorId = authorDao.insert(book.getAuthor());
        long bookId = bookDao.insert(book);

        authorDao.updateWithBookRelation(authorId, bookId);
        bookDao.updateWithAuthorRelation(bookId, authorId);
        bookDao.updateWithGenreRelation(bookId, genreId);

        return bookId;
    }

    public Book getById(long id) {
        return bookDao.getById(id);
    }

    public void update(Book book) {

    }

    public void delete(Book book) {

    }
}
