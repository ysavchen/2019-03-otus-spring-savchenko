package com.mycompany.hw_l25_spring_batch;

import com.mycompany.hw_l25_spring_batch.domain.mongo.FlightMongo;
import com.mycompany.hw_l25_spring_batch.domain.mongo.TicketMongo;
import com.mycompany.hw_l25_spring_batch.repositories.FlightMongoRepository;
import com.mycompany.hw_l25_spring_batch.repositories.TicketMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
public class LoadDataToMongoJobTests {

    public static final String JOB_NAME = "loadDataToMongo";

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private FlightMongoRepository flightMongoRepository;

    @Autowired
    private TicketMongoRepository ticketMongoRepository;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    void loadDataToMongoJob() throws Exception {
        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo(JOB_NAME);

        JobExecution execution = jobLauncherTestUtils.launchJob(new JobParameters());
        assertThat(execution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        List<FlightMongo> flights = flightMongoRepository.findAll();
        assertThat(flights).hasSize(20);

        List<TicketMongo> tickets = ticketMongoRepository.findAll();
        assertThat(tickets).hasSize(30);
    }
}
