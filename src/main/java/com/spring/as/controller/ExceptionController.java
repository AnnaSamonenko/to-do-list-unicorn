package com.spring.as.controller;

import com.spring.as.validation.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({IllegalArgumentException.class, UsernameNotFoundException.class})
    @ResponseBody
    ResponseEntity handleBadRequest(Exception ex) {
        return new ResponseEntity<>(new ErrorDetails(HttpStatus.BAD_REQUEST.toString(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
