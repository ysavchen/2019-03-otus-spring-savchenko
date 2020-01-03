package com.mycompany.hw_l02_spring_config;

import com.mycompany.hw_l02_spring_config.domain.User;
import com.mycompany.hw_l02_spring_config.service.IOService;
import com.mycompany.hw_l02_spring_config.service.UserDataService;
import com.mycompany.hw_l02_spring_config.service.UserDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDataServiceImplTests {

    @Mock
    private IOService ioService;

    private UserDataService userDataService;

    @BeforeEach
    void setUp() {
        userDataService = new UserDataServiceImpl(ioService);
    }

    @Test
    void getUserTest() {
        var user = new User("John", "Smith");
        doNothing().when(ioService).out("Please, enter your name:");
        doNothing().when(ioService).out("\nPlease, enter your surname:");
        when(ioService.readString())
                .thenReturn("John")
                .thenReturn("Smith");

        assertEquals(user, userDataService.getUser());
    }
}
