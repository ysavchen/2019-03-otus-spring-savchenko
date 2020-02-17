package com.mycompany.hw_l09_spring_orm_jpa.service;

import com.mycompany.hw_l09_spring_orm_jpa.repositories.AuthorRepository;
import com.mycompany.hw_l09_spring_orm_jpa.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorDbServiceImpl implements AuthorDbService {

    private final AuthorRepository repository;

    @Override
    public long insert(Author author) {
        return repository.insert(author);
    }

    @Override
    public Optional<Author> getById(long id) {
        return repository.getById(id);
    }
}
