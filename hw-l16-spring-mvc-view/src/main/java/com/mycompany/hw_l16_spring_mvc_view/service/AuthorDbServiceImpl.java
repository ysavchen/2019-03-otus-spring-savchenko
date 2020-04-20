package com.mycompany.hw_l16_spring_mvc_view.service;

import com.mycompany.hw_l16_spring_mvc_view.domain.Author;
import com.mycompany.hw_l16_spring_mvc_view.repositories.AuthorRepository;
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
