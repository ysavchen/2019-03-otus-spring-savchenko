package com.mycompany.hw_l01_spring_introduction;

import com.mycompany.hw_l01_spring_introduction.dao.Storage;
import com.mycompany.hw_l01_spring_introduction.domain.GivenAnswer;
import com.mycompany.hw_l01_spring_introduction.domain.Person;
import com.mycompany.hw_l01_spring_introduction.exceptions.QuestionMismatchException;
import com.mycompany.hw_l01_spring_introduction.service.PrintService;
import com.mycompany.hw_l01_spring_introduction.service.ReadService;
import com.mycompany.hw_l01_spring_introduction.service.ResultAnalyzerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestingApp {

    private final PrintService printService;
    private final ReadService readService;
    private final Storage storage;
    private final ResultAnalyzerService resultAnalyzer;

    public void go() {
        printService.print("Please, enter your name:");
        String name = readService.read();

        printService.print("\nPlease, enter your surname:");
        String surname = readService.read();

        Person person = new Person(name, surname);
        storage.getQuestions().forEach(question -> {
            storage.getOptions()
                    .stream()
                    .filter(opts -> opts.getQuestionId() == question.getId())
                    .findFirst()
                    .ifPresentOrElse(
                            opts -> {
                                System.out.println("\n" + question.getText());
                                opts.getValues().forEach(System.out::println);
                            },
                            () -> {
                                throw new QuestionMismatchException(
                                        "No relevant options found for question (id = " + question.getId() + ")");
                            });

            var answer = new GivenAnswer(question.getId(), readService.read());
            resultAnalyzer.checkAnswer(answer);
        });

        printResults(person);
    }

    private void printResults(Person person) {
        int numQuestions = storage.getQuestions().size();
        int numCorrectAnswers = resultAnalyzer.getNumCorrectAnswers();
        printService.print("\nTest result for " + person.getName() + " " + person.getSurname());
        printService.print("Number of questions: " + numQuestions);
        printService.print("Correct answers: " + numCorrectAnswers);
        printService.print("Incorrect answers: " + (numQuestions - numCorrectAnswers));
    }
}
