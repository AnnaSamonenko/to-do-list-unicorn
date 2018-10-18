package com.spring.as.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    String home() {
        return "index";
    }

    @GetMapping("/register")
    String register() {
        return "register";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }
}
