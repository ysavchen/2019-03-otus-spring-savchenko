package com.mycompany.hw_l01_spring_introduction;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TestingApp {

    private int numCorrectAnswers;
    private String[] headers = {
            "question", "option1", "option2",
            "option3", "option4", "correct_answer"
    };

    public static void main(String[] args) {
        new TestingApp().go();
    }

    @SneakyThrows
    private void go() {
        @Cleanup Scanner in = new Scanner(System.in);
        System.out.println("Please, enter your name:");
        String name = in.nextLine();

        System.out.println("Please, enter your surname:");
        String surname = in.nextLine();

        System.out.println("Your name is " + name + " " + surname);

        @Cleanup InputStream is = this.getClass().getResourceAsStream("/questions.csv");
        Reader reader = new InputStreamReader(is);
        Iterable<CSVRecord> records = CSVFormat.INFORMIX_UNLOAD
                .withHeader(headers)
                .withFirstRecordAsHeader()
                .parse(reader);

        for (var record : records) {
            var question = List.of(
                    record.get("question"),
                    record.get("option1"),
                    record.get("option2"),
                    record.get("option3"),
                    record.get("option4")
            );
            question.forEach(System.out::println);

            String answer = in.nextLine();
            String correctAnswer = record.get("correct_answer");
            if (Objects.equals(answer, correctAnswer)) {
                numCorrectAnswers++;
            }
        }

        int numQuestions = IterableUtils.size(records);
        System.out.println("Number of questions: " + numQuestions);
        System.out.println("Correct answers: " + numCorrectAnswers);
        System.out.println("Incorrect answers: " + (numQuestions - numCorrectAnswers));
    }
}
