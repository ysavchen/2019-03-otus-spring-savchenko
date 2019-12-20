package com.mycompany.hw_l01_spring_introduction.service;

import com.mycompany.hw_l01_spring_introduction.domain.GivenAnswer;

public interface ResultAnalyzerService {

    void checkAnswer(GivenAnswer correctAnswer);

    int getNumCorrectAnswers();
}
