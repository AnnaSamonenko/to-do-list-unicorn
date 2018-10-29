package com.spring.as.controller.rest;

import com.spring.as.entity.User;
import com.spring.as.service.UserService;
import com.spring.as.validation.ErrorDetails;
import com.spring.as.validation.RegistrationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationValidation registrationValidation;

    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @PostMapping("/add")
    public ResponseEntity<?> register(@RequestBody User user, BindingResult bindingResult) {
        registrationValidation.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.toString(), bindingResult);
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
            //ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody.toString());
        }

        user.setRole("user");
        userService.create(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok(user);
    }

}
