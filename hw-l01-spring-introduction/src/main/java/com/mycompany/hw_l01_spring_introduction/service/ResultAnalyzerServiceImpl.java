package com.mycompany.hw_l01_spring_introduction.service;

import com.mycompany.hw_l01_spring_introduction.dao.Storage;
import com.mycompany.hw_l01_spring_introduction.domain.GivenAnswer;
import com.mycompany.hw_l01_spring_introduction.domain.Person;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class ResultAnalyzerServiceImpl implements ResultAnalyzerService {

    private final Storage storage;
    private final PrintService printService;
    private Person person;
    private int numCorrectAnswers;

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void checkAnswer(GivenAnswer answer) {
        storage.getCorrectAnswers().stream()
                .filter(correctAnswer -> answer.getQuestionId() == correctAnswer.getId())
                .findFirst()
                .ifPresentOrElse(
                        correctAnswer -> {
                            if (Objects.equals(correctAnswer.getText(), answer.getText())) {
                                numCorrectAnswers++;
                            }
                        },
                        () -> {
                            throw new IllegalArgumentException("No question with id = " + answer.getQuestionId());
                        });
    }

    @Override
    public void printResults() {
        int numQuestions = storage.getQuestions().size();
        printService.print("\nTest result for " + person.getName() + " " + person.getSurname());
        printService.print("Number of questions: " + numQuestions);
        printService.print("Correct answers: " + numCorrectAnswers);
        printService.print("Incorrect answers: " + (numQuestions - numCorrectAnswers));
    }
}
