package com.mycompany.hw_l01_spring_introduction.service;

import java.io.PrintStream;

public class ConsolePrintService implements PrintService {

    private final PrintStream out = System.out;

    @Override
    public void print(String text) {
        out.println(text);
    }

}
