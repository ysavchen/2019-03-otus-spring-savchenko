package com.mycompany.hw_l02_spring_config;

import com.mycompany.hw_l02_spring_config.app.TestingApp;
import lombok.val;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        val context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestingApp app = context.getBean(TestingApp.class);
        app.go();
    }
}
