package com.spring.as.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/index")
    String index(@RequestParam("project_id") String projectId) {
        return "index";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

}
