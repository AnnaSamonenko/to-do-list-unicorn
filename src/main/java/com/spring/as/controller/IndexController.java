package com.spring.as.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    String index() {
        return "index";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

}
