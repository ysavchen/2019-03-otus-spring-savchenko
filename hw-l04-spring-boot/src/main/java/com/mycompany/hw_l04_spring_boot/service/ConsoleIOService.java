package com.mycompany.hw_l04_spring_boot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsoleIOService implements IOService {

    private final ConsoleContext console;

    @Override
    public void out(String text) {
        console.out().println(text);
    }

    @Override
    public String readString() {
        return console.scanner().nextLine();
    }

}
