package com.mycompany.hw_l34_srping_cloud.service;

import com.mycompany.hw_l34_srping_cloud.domain.Author;
import com.mycompany.hw_l34_srping_cloud.repositories.AuthorRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorDbServiceImpl implements AuthorDbService {

    private final AuthorRepository repository;

    @HystrixCommand(
            groupKey = "library",
            fallbackMethod = "getAuthorForNonAvailable"
    )
    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getById(long id) {
        return repository.findById(id);
    }

    public Optional<Author> getAuthorForNonAvailable() {
        return Optional.of(new Author(0L, "N/A", "N/a"));
    }
}
