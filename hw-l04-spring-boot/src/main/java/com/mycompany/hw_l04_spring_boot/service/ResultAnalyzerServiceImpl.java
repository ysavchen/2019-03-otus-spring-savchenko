package com.mycompany.hw_l04_spring_boot.service;

import com.mycompany.hw_l04_spring_boot.dao.Storage;
import com.mycompany.hw_l04_spring_boot.domain.Answer;
import com.mycompany.hw_l04_spring_boot.domain.Question;
import com.mycompany.hw_l04_spring_boot.exceptions.QuestionMismatchException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultAnalyzerServiceImpl implements ResultAnalyzerService {

    private final Storage storage;
    private int numCorrectAnswers;

    @Override
    public void checkAnswer(@NonNull Answer answer) {
        storage.getQuestions().stream()
                .map(Question::getCorrectAnswer)
                .filter(correctAnswer -> answer.getQuestionId() == correctAnswer.getQuestionId())
                .findFirst()
                .ifPresentOrElse(
                        correctAnswer -> {
                            if (correctAnswer.getText().equalsIgnoreCase(answer.getText())) numCorrectAnswers++;
                        },
                        () -> {
                            throw new QuestionMismatchException("No question with id = " + answer.getQuestionId());
                        }
                );
    }

    @Override
    public int getNumCorrectAnswers() {
        return numCorrectAnswers;
    }
}
