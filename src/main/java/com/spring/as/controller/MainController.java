package com.spring.as.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/tasks")
    String tasks() {
        return "tasks";
    }

    @GetMapping("/registration")
    String register() {
        return "registration";
    }
}
