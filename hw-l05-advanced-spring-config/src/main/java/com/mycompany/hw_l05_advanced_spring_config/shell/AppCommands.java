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
    private boolean isStarted = false;
    private boolean isAnswerExpected = false;

    @ShellMethod(value = "Start test for a user", key = {"stf", "start-test-for"})
    public String startTestFor(String name, String surname) {
        testingService.setUser(new User(name, surname));
        isStarted = true;
        isAnswerExpected = true;
        return testingService.next();
    }

    @ShellMethod(value = "Answer to a question", key = {"a", "answer"})
    public String answer(String answer) {
        isAnswerExpected = false;
        return testingService.acceptAnswer(answer);
    }

    @ShellMethod(value = "Go to the next step", key = {"n", "next"})
    public String next() {
        isAnswerExpected = true;
        return testingService.next();
    }

    private Availability nextAvailability() {
        return isStarted ? Availability.available() :
                Availability.unavailable("a test is not started");
    }

    private Availability answerAvailability() {
        return isAnswerExpected ? Availability.available() :
                Availability.unavailable("there is no question to answer");
    }
}
