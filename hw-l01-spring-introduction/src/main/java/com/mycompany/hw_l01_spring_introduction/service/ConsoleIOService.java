package com.mycompany.hw_l01_spring_introduction.service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsoleIOService implements IOService {

    private final ConsoleContext console;

    @Override
    public void out(String text) {
        console.out.println(text);
    }

    @Override
    public String readString() {
        return console.scanner.nextLine();
    }

}
