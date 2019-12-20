package com.mycompany.hw_l01_spring_introduction.service;

import java.util.Scanner;

public class ConsoleReadService implements ReadService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
