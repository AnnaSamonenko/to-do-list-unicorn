package com.spring.as.controller;

import com.spring.as.model.User;
import com.spring.as.model.VerificationToken;
import com.spring.as.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

@Controller
public class MainController {

    @Autowired
    private IUserService userService;

    @GetMapping("/index")
    String index() {
        return "index";
    }

    @GetMapping("/registration")
    String registration() {
        return "registration";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping(value = "/registration-confirmation")
    public ResponseEntity confirmRegistration(@RequestParam("token") String token) {

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null)
            throw new IllegalArgumentException("Invalid token");

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0)
            throw new IllegalArgumentException("Your verification token already expired");

        user.setEnabled(true);
        userService.update(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
