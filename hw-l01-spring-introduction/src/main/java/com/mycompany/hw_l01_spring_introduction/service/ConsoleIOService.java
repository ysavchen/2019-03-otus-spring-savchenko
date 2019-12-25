package com.mycompany.hw_l01_spring_introduction.service;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIOService implements IOService {

    private final PrintStream out = System.out;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void out(String text) {
        out.println(text);
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }

}
