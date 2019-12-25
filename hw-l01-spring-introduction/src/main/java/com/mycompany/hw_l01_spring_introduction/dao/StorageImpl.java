package com.mycompany.hw_l01_spring_introduction.dao;

import com.mycompany.hw_l01_spring_introduction.domain.Answer;
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

    @Override
    public List<Question> getQuestions() {
        return getRecords(questionsPath).stream()
                .map(record -> {
                    int id = parseInt(record.get("id"));
                    return new Question(
                            id,
                            record.get("question"),
                            List.of(record.get("options").split(",")),
                            new Answer(id, record.get("correctAnswer"))
                    );
                })
                .collect(toList());
    }

    @SneakyThrows
    private List<CSVRecord> getRecords(String path) {
        @Cleanup InputStream is = this.getClass().getResourceAsStream(path);
        Reader reader = new InputStreamReader(is);
        return CSVFormat.INFORMIX_UNLOAD
                .withFirstRecordAsHeader()
                .parse(reader)
                .getRecords();
    }
}
