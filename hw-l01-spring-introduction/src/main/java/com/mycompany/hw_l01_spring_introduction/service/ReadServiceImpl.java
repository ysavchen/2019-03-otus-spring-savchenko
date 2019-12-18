package com.mycompany.hw_l01_spring_introduction.service;

import java.util.Scanner;

public class ReadServiceImpl implements ReadService {

    private final Scanner scanner;

    public ReadServiceImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
