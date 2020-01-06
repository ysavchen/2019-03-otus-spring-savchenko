package com.mycompany.hw_l02_spring_config.service;

import com.mycompany.hw_l02_spring_config.domain.Answer;

public interface ResultAnalyzerService {

    void checkAnswer(Answer answer);

    int getNumCorrectAnswers();
}
