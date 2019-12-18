package com.mycompany.hw_l01_spring_introduction.service;

import com.mycompany.hw_l01_spring_introduction.domain.GivenAnswer;
import com.mycompany.hw_l01_spring_introduction.domain.Person;

public interface ResultAnalyzerService {

    void setPerson(Person person);

    void checkAnswer(GivenAnswer correctAnswer);

    void printResults();
}
