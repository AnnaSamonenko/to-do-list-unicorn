package com.spring.as.controller.rest;

import com.spring.as.mail.OnRegistrationCompleteEvent;
import com.spring.as.model.User;
import com.spring.as.model.VerificationToken;
import com.spring.as.service.IUserService;
import com.spring.as.validation.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody @Valid User user, BindingResult bindingResult, WebRequest request) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ErrorDetails(HttpStatus.BAD_REQUEST.toString(), bindingResult),
                    HttpStatus.BAD_REQUEST);

        User registered = userService.createAccount(user);

        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return new ResponseEntity(HttpStatus.CREATED);
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
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity deleteAccount(@PathVariable("username") String username) {
        userService.deleteAccount(username);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
