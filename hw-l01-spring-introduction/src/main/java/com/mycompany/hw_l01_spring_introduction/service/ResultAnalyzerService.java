package com.mycompany.hw_l01_spring_introduction.service;

import com.mycompany.hw_l01_spring_introduction.domain.Answer;

public interface ResultAnalyzerService {

    void checkAnswer(Answer correctAnswer);

    int getNumCorrectAnswers();
}
