package com.mycompany.hw_l01_spring_introduction;

import com.mycompany.hw_l01_spring_introduction.dao.Storage;
import com.mycompany.hw_l01_spring_introduction.domain.Answer;
import com.mycompany.hw_l01_spring_introduction.domain.User;
import com.mycompany.hw_l01_spring_introduction.exceptions.QuestionMismatchException;
import com.mycompany.hw_l01_spring_introduction.service.PrintService;
import com.mycompany.hw_l01_spring_introduction.service.ReadService;
import com.mycompany.hw_l01_spring_introduction.service.ResultAnalyzerService;
import com.mycompany.hw_l01_spring_introduction.service.UserDataService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestingApp {

    private final UserDataService userDataService;
    private final PrintService printService;
    private final ReadService readService;
    private final Storage storage;
    private final ResultAnalyzerService resultAnalyzer;

    public void go() {
        User user = userDataService.getUser();
        storage.getQuestions().forEach(question -> {
            storage.getOptions()
                    .stream()
                    .filter(opts -> opts.getQuestionId() == question.getId())
                    .findFirst()
                    .ifPresentOrElse(
                            opts -> {
                                printService.print("\n" + question.getText());
                                opts.getValues().forEach(printService::print);
                            },
                            () -> {
                                throw new QuestionMismatchException(
                                        "No relevant options found for question (id = " + question.getId() + ")");
                            });

            var answer = new Answer(question.getId(), readService.read());
            resultAnalyzer.checkAnswer(answer);
        });

        printResults(user);
    }

    private void printResults(User user) {
        int numQuestions = storage.getQuestions().size();
        int numCorrectAnswers = resultAnalyzer.getNumCorrectAnswers();
        printService.print("\nTest result for " + user.getName() + " " + user.getSurname());
        printService.print("Number of questions: " + numQuestions);
        printService.print("Correct answers: " + numCorrectAnswers);
        printService.print("Incorrect answers: " + (numQuestions - numCorrectAnswers));
    }
}
