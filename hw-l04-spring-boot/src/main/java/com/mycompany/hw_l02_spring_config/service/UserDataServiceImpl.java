package com.mycompany.hw_l02_spring_config.service;

import com.mycompany.hw_l02_spring_config.app.AppLocale;
import com.mycompany.hw_l02_spring_config.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {

    private final IOService ioService;
    private final AppLocale locale;

    @Override
    public User getUser() {
        ioService.out(locale.getValue("user.name"));
        String name = ioService.readString();

        ioService.out("\n" + locale.getValue("user.surname"));
        String surname = ioService.readString();

        return new User(name, surname);
    }
}
