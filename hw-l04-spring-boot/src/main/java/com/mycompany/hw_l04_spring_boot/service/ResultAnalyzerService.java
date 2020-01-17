package com.mycompany.hw_l04_spring_boot.service;

import com.mycompany.hw_l04_spring_boot.domain.Answer;

public interface ResultAnalyzerService {

    void checkAnswer(Answer answer);

    int getNumCorrectAnswers();
}
