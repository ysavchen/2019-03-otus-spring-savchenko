package com.mycompany.hw_l02_spring_config.service;

import com.mycompany.hw_l02_spring_config.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {

    private final IOService ioService;

    @Override
    public User getUser() {
        ioService.out("Please, enter your name:");
        String name = ioService.readString();

        ioService.out("\nPlease, enter your surname:");
        String surname = ioService.readString();

        return new User(name, surname);
    }
}
