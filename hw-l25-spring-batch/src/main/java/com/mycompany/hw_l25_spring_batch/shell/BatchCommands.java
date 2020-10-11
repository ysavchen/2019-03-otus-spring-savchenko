package com.mycompany.hw_l25_spring_batch.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class BatchCommands {

    private final Job loadDataToMongo;
    private final JobLauncher jobLauncher;

    @ShellMethod(value = "startLoadDataToMongoJob", key = "sld")
    public void startLoadDataToMongoJob() throws Exception {
        JobExecution execution = jobLauncher.run(loadDataToMongo, new JobParameters());
        System.out.println(execution);
    }

}
