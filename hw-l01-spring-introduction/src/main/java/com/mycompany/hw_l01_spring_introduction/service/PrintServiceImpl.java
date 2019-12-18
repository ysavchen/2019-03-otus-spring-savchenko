package com.mycompany.hw_l01_spring_introduction.service;

public class PrintServiceImpl implements PrintService {

    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
