package com.mycompany.hw_l02_spring_config.app;

import com.mycompany.hw_l02_spring_config.dao.Storage;
import com.mycompany.hw_l02_spring_config.domain.Answer;
import com.mycompany.hw_l02_spring_config.domain.User;
import com.mycompany.hw_l02_spring_config.service.IOService;
import com.mycompany.hw_l02_spring_config.service.ResultAnalyzerService;
import com.mycompany.hw_l02_spring_config.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestingApp {

    private final UserDataService userDataService;
    private final IOService ioService;
    private final Storage storage;
    private final ResultAnalyzerService resultAnalyzer;

    public void go() {
        User user = userDataService.getUser();
        storage.getQuestions().forEach(question -> {
            ioService.out("\n" + question.getText());
            question.getOptions().forEach(ioService::out);
            Answer answer = new Answer(question.getId(), ioService.readString());
            resultAnalyzer.checkAnswer(answer);
        });

        printResults(user);
    }

    private void printResults(User user) {
        int numQuestions = storage.getQuestions().size();
        int numCorrectAnswers = resultAnalyzer.getNumCorrectAnswers();
        ioService.out("\nTest result for " + user.getName() + " " + user.getSurname());
        ioService.out("Number of questions: " + numQuestions);
        ioService.out("Correct answers: " + numCorrectAnswers);
        ioService.out("Incorrect answers: " + (numQuestions - numCorrectAnswers));
    }
}
