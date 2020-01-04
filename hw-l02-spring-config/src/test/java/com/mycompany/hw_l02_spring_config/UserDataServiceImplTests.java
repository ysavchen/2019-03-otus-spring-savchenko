package com.mycompany.hw_l02_spring_config;

import com.mycompany.hw_l02_spring_config.app.AppLocale;
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

    @Mock
    private AppLocale locale;

    private UserDataService userDataService;

    @BeforeEach
    void setUp() {
        userDataService = new UserDataServiceImpl(ioService, locale);
    }

    @Test
    void getUserTest() {
        when(locale.getValue("user.name")).thenReturn("");
        when(locale.getValue("user.surname")).thenReturn("");
        doNothing().when(ioService).out("");
        doNothing().when(ioService).out("\n");

        var user = new User("John", "Smith");
        when(ioService.readString())
                .thenReturn("John")
                .thenReturn("Smith");

        assertEquals(user, userDataService.getUser());
    }
}
