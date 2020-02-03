package com.mycompany.hw_l09_spring_orm_jpa.service;

import com.mycompany.hw_l09_spring_orm_jpa.repositories.AuthorRepository;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorDbServiceImpl implements AuthorDbService {

    private final AuthorRepository authorRepository;

    @Override
    public long insert(Author author) {
        return authorRepository.insert(author);
    }

    @Override
    public Optional<Author> getById(long id) {
        return authorRepository.getById(id);
    }
}
