package com.mycompany.hw_l29_spring_boot_actuator.service;

import com.mycompany.hw_l29_spring_boot_actuator.domain.Author;
import com.mycompany.hw_l29_spring_boot_actuator.repositories.AuthorRepository;
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
