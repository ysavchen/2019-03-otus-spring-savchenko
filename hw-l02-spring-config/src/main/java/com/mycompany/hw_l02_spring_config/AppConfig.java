package com.mycompany.hw_l02_spring_config;

import com.mycompany.hw_l02_spring_config.app.TestingApp;
import com.mycompany.hw_l02_spring_config.dao.Storage;
import com.mycompany.hw_l02_spring_config.dao.StorageImpl;
import com.mycompany.hw_l02_spring_config.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    Storage storage(@Value("${questionsPath}") String questionsPath) {
        return new StorageImpl(questionsPath);
    }

    @Bean
    ConsoleContext consoleContext() {
        return new ConsoleContext();
    }

    @Bean
    IOService ioService(ConsoleContext consoleContext) {
        return new ConsoleIOService(consoleContext);
    }

    @Bean
    ResultAnalyzerService resultAnalyzer(Storage storage) {
        return new ResultAnalyzerServiceImpl(storage);
    }

    @Bean
    UserDataService userDataService(IOService ioService) {
        return new UserDataServiceImpl(ioService);
    }

    @Bean
    TestingApp testingApp(UserDataService userDataService, IOService ioService,
                          Storage storage, ResultAnalyzerService resultAnalyzer) {
        return new TestingApp(userDataService, ioService, storage, resultAnalyzer);
    }
}
