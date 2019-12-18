package com.mycompany.hw_l01_spring_introduction;

import com.mycompany.hw_l01_spring_introduction.dao.Storage;
import com.mycompany.hw_l01_spring_introduction.domain.Person;
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

        resultAnalyzer.setPerson(new Person(name, surname));
        storage.getQuestions().forEach(question -> {

        });

        resultAnalyzer.printResults();
    }
}
