package com.mycompany.hw_l01_spring_introduction.service;

import com.mycompany.hw_l01_spring_introduction.domain.User;
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
