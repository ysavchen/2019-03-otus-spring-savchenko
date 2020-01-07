package com.mycompany.hw_l05_advanced_spring_config;

import com.mycompany.hw_l05_advanced_spring_config.service.ConsoleContext;
import com.mycompany.hw_l05_advanced_spring_config.service.ConsoleIOService;
import com.mycompany.hw_l05_advanced_spring_config.service.IOService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsoleIOServiceTests {

    @Mock
    private ConsoleContext consoleContext;

    @Mock
    private PrintStream printStream;

    private IOService ioService;

    @BeforeEach
    void setUp() {
        ioService = new ConsoleIOService(consoleContext);
    }

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
