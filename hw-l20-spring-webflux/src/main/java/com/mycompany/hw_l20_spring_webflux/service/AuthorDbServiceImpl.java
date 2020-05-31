package com.mycompany.hw_l20_spring_webflux.service;

import com.mycompany.hw_l20_spring_webflux.domain.Author;
import com.mycompany.hw_l20_spring_webflux.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorDbServiceImpl implements AuthorDbService {

    private final AuthorRepository repository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getById(long id) {
        return repository.findById(id);
    }
}
