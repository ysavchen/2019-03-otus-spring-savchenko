package com.mycompany.hw_l05_advanced_spring_config.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private final int id;
    private final String text;
    private final List<String> options;
    private final Answer correctAnswer;

}
