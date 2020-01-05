package com.mycompany.hw_l02_spring_config.domain;

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
