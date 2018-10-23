package com.spring.as.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    String tasks() {
        return "index";
    }

    @GetMapping("/registration")
    String register() {
        return "registration";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }
}
