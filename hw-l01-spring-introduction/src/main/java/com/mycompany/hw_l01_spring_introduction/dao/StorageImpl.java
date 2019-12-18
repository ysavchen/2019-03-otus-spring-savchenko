package com.mycompany.hw_l01_spring_introduction.dao;

import com.mycompany.hw_l01_spring_introduction.domain.CorrectAnswer;
import com.mycompany.hw_l01_spring_introduction.domain.Options;
import com.mycompany.hw_l01_spring_introduction.domain.Question;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class StorageImpl implements Storage {

    private final String questionsPath;
    private final String optionsPath;
    private final String answersPath;

    @Override
    public List<Question> getQuestions() {
        return getRecords(questionsPath).stream()
                .map(record -> new Question(parseInt(record.get("id")), record.get("question")))
                .collect(toList());
    }

    @Override
    public List<Options> getOptions() {
        return getRecords(optionsPath).stream()
                .map(record -> new Options(
                        parseInt(record.get("id")),
                        List.of(
                                record.get("option1"),
                                record.get("option2"),
                                record.get("option3"),
                                record.get("option4")
                        ))
                )
                .collect(toList());
    }

    @Override
    public List<CorrectAnswer> getCorrectAnswers() {
        return getRecords(answersPath).stream()
                .map(record -> new CorrectAnswer(parseInt(record.get("id")), record.get("answer")))
                .collect(toList());
    }

    @SneakyThrows
    public List<CSVRecord> getRecords(String path) {
        @Cleanup InputStream is = this.getClass().getResourceAsStream(path);
        Reader reader = new InputStreamReader(is);
        return CSVFormat.INFORMIX_UNLOAD
                .withFirstRecordAsHeader()
                .parse(reader)
                .getRecords();
    }
}
