package com.mycompany.hw_l01_spring_introduction.service;

import com.mycompany.hw_l01_spring_introduction.dao.Storage;
import com.mycompany.hw_l01_spring_introduction.domain.GivenAnswer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResultAnalyzerServiceImpl implements ResultAnalyzerService {

    private final Storage storage;
    private int numCorrectAnswers;

    @Override
    public void checkAnswer(@NonNull GivenAnswer answer) {
        storage.getCorrectAnswers().stream()
                .filter(correctAnswer -> answer.getQuestionId() == correctAnswer.getQuestionId())
                .findFirst()
                .ifPresentOrElse(
                        correctAnswer -> {
                            if (answer.getText().compareToIgnoreCase(correctAnswer.getText()) == 0) numCorrectAnswers++;
                        },
                        () -> {
                            throw new IllegalArgumentException("No question with id = " + answer.getQuestionId());
                        }
                );
    }

    @Override
    public int getNumCorrectAnswers() {
        return numCorrectAnswers;
    }
}
