package com.mycompany.hw_l05_advanced_spring_config;

import com.mycompany.hw_l05_advanced_spring_config.service.ConsoleContext;
import com.mycompany.hw_l05_advanced_spring_config.service.IOService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ConsoleIOServiceTests {

    @MockBean
    private ConsoleContext consoleContext;

    @MockBean
    private PrintStream printStream;

    @Autowired
    private IOService ioService;

    @Test
    void testOut() {
        var captor = ArgumentCaptor.forClass(String.class);
        when(consoleContext.out()).thenReturn(printStream);
        doNothing().when(printStream).println(captor.capture());
        ioService.out("test");

        assertEquals("test", captor.getValue());
    }

    @Test
    void testReadString() {
        var scanner = new Scanner("test");
        when(consoleContext.scanner()).thenReturn(scanner);
        assertEquals("test", ioService.readString());
    }
}
