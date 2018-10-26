package com.spring.as.validation;

import com.spring.as.dao.UserDAOImpl;
import com.spring.as.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.apache.commons.validator.routines.EmailValidator;

@Component
public class RegistrationValidation implements Validator {

    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private UserDAOImpl userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.not_empty");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 10)
            errors.rejectValue("username", "username.invalid_range");
        if (userDAO.read(user.getUsername()) != null)
            errors.rejectValue("username", "username.isPresent");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.not_empty");
        if (user.getPassword().length() < 6 || user.getPassword().length() > 20)
            errors.rejectValue("password", "password.invalid_range");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.not_empty");
        // TODO: fix this
        if (userDAO.read(user.getUsername()) != null)
            errors.rejectValue("email", "email.isPresent");
        if (!emailValidator.isValid(user.getEmail()))
            errors.rejectValue("email", "email.incorrect_value");
    }
}
