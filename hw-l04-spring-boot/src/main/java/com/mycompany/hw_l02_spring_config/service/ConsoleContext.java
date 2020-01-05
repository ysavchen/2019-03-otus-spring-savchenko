package com.mycompany.hw_l02_spring_config.service;

import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Scanner;

@Component
public class ConsoleContext {

    private final PrintStream out = System.out;
    private final Scanner scanner = new Scanner(System.in);

    public PrintStream out() {
        return out;
    }

    public Scanner scanner() {
        return scanner;
    }
}
