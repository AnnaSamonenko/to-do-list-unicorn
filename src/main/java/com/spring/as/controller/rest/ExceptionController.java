package com.spring.as.controller.rest;

import com.spring.as.validation.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    ResponseEntity handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ResponseEntity<>(new ErrorDetails(HttpStatus.BAD_REQUEST.toString(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
