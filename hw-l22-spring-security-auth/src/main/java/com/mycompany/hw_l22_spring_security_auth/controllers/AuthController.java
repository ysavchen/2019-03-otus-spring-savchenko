package com.mycompany.hw_l22_spring_security_auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private static final String LOGIN_FORM = "login";

    @GetMapping("/login")
    public String getLoginForm() {
        return LOGIN_FORM;
    }
}
