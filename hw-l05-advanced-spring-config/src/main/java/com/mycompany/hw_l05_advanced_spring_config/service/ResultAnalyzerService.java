package com.mycompany.hw_l05_advanced_spring_config.service;

import com.mycompany.hw_l05_advanced_spring_config.domain.Answer;

public interface ResultAnalyzerService {

    boolean checkAnswer(Answer answer);

    int getNumCorrectAnswers();
}
