package com.mycompany.hw_l01_spring_introduction;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;

public class Main {

    private int numCorrectAnswers;

    public static void main(String[] args) {
        val context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestingApp app = context.getBean(TestingApp.class);
        app.go();
    }

    @SneakyThrows
    private void go() {
        @Cleanup Scanner in = new Scanner(System.in);
        System.out.println("Please, enter your name:");
        String name = in.nextLine();

        System.out.println("\nPlease, enter your surname:");
        String surname = in.nextLine();

        @Cleanup InputStream is = this.getClass().getResourceAsStream("/questions.csv");
        Reader reader = new InputStreamReader(is);
        List<CSVRecord> records = CSVFormat.INFORMIX_UNLOAD
                .withFirstRecordAsHeader()
                .parse(reader)
                .getRecords();

        for (var record : records) {
            var question = record.get("question");
            var options = List.of(
                    record.get("option1"),
                    record.get("option2"),
                    record.get("option3"),
                    record.get("option4")
            );
            System.out.println("\n" + question);
            options.forEach(System.out::println);

            String answer = in.nextLine();
            String correctAnswer = record.get("correct_answer");
            if (answer.compareToIgnoreCase(correctAnswer) == 0) {
                numCorrectAnswers++;
            }
        }

        System.out.println("\nTest result for " + name + " " + surname);
        System.out.println("Number of questions: " + records.size());
        System.out.println("Correct answers: " + numCorrectAnswers);
        System.out.println("Incorrect answers: " + (records.size() - numCorrectAnswers));
    }
}
