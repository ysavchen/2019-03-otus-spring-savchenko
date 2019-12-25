package com.mycompany.hw_l01_spring_introduction.service;

import com.mycompany.hw_l01_spring_introduction.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {

    private final PrintService printService;
    private final ReadService readService;

    @Override
    public User getUser() {
        printService.print("Please, enter your name:");
        String name = readService.read();

        printService.print("\nPlease, enter your surname:");
        String surname = readService.read();

        return new User(name, surname);
    }
}
