package com.mycompany.hw_l25_spring_batch.service;

import com.mycompany.hw_l25_spring_batch.domain.Author;
import com.mycompany.hw_l25_spring_batch.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorDbServiceImpl implements AuthorDbService {

    private final AuthorRepository repository;

    @Override
    public String save(Author author) {
        return repository.save(author).getId();
    }

    @Override
    public Optional<Author> getById(String id) {
        return repository.findById(id);
    }
}
