package com.mycompany.hw_l05_advanced_spring_config;

import com.mycompany.hw_l05_advanced_spring_config.app.TestingApp;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        val context = SpringApplication.run(Main.class);
        TestingApp app = context.getBean(TestingApp.class);
        app.go();
    }
}
