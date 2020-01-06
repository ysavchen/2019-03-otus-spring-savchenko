package com.mycompany.hw_l02_spring_config.service;

import com.mycompany.hw_l02_spring_config.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {

    private final IOService ioService;
    private final MessageSourceService msService;

    @Override
    public User getUser() {
        ioService.out(msService.getMessage("user.name"));
        String name = ioService.readString();

        ioService.out("\n" + msService.getMessage("user.surname"));
        String surname = ioService.readString();

        return new User(name, surname);
    }
}
