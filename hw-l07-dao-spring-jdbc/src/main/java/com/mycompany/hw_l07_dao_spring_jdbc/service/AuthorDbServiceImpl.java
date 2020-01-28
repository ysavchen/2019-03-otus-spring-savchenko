package com.mycompany.hw_l07_dao_spring_jdbc.service;

import com.mycompany.hw_l07_dao_spring_jdbc.dao.AuthorDao;
import com.mycompany.hw_l07_dao_spring_jdbc.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorDbServiceImpl implements AuthorDbService {

    private final AuthorDao authorDao;

    @Override
    public long insert(Author author) {
        long authorId = authorDao.insert(author);

        return authorId;
    }

    @Override
    public Optional<Author> getById(long id) {
        return authorDao.getById(id);
    }
}
