package com.spring.as.validation;

import com.spring.as.dto.AddTaskDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddTaskValidationForm implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AddTaskDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
