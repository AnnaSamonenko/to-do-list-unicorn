package com.spring.as.validation;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ErrorDetails {

    @Getter
    private String status;
    @Getter
    private List<String> messages = new ArrayList<>();

    public ErrorDetails(String status, BindingResult bindingResult) {
        this.status = status;
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fe : fieldErrors)
            messages.add(fe.getDefaultMessage());
    }

    public ErrorDetails(String status, String message) {
        this.status = status;
        messages.add(message);
    }

}
