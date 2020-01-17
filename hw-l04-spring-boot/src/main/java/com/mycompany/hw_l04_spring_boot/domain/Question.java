package com.mycompany.hw_l04_spring_boot.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Question {

    private final int id;
    private final String text;
    private final List<String> options;
    private final Answer correctAnswer;

}
