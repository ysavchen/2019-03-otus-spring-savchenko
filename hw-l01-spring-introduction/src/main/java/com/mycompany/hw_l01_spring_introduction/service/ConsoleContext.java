package com.mycompany.hw_l01_spring_introduction.service;

import java.io.PrintStream;
import java.util.Scanner;

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
