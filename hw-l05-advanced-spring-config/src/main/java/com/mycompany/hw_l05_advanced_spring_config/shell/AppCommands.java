package com.mycompany.hw_l05_advanced_spring_config.shell;

import com.mycompany.hw_l05_advanced_spring_config.domain.User;
import com.mycompany.hw_l05_advanced_spring_config.service.TestingService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class AppCommands {

    private final TestingService testingService;

    @ShellMethod(value = "Start test for a user", key = {"stf", "start-test-for"})
    public String startTestFor(String name, String surname) {
        testingService.setUser(new User(name, surname));
        return testingService.nextQuestion();
    }

    @ShellMethod(value = "Answer to a question", key = {"a", "answer"})
    //@ShellMethodAvailability("isAnswerAvailable")
    public String answer(String answer) {
        return testingService.acceptAnswer(answer);
    }

    @ShellMethod(value = "Get next question", key = {"n", "next"})
    //@ShellMethodAvailability("isNextAvailable")
    public String next() {
        return testingService.nextQuestion();
    }

    @ShellMethod(value = "Get test results", key = {"r", "results"})
    //@ShellMethodAvailability("resultsAvailable")
    public String results() {
        return testingService.getResults();
    }

    private Availability isNextAvailable() {
        return null;
    }

    private Availability isAnswerAvailable() {
        return null;
    }

    private Availability resultsAvailable() {
        return null;
    }
}
