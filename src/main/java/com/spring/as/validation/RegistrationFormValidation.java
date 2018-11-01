package com.spring.as.validation;

import com.spring.as.dao.UserDAOImpl;
import com.spring.as.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import org.apache.commons.validator.routines.EmailValidator;

@Component
@PropertySource("classpath:validation.properties")
public class RegistrationFormValidation implements Validator {

    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private UserDAOImpl userDAO;

    @Autowired
    private Environment env;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", env.getProperty("username.not_empty"));
        if (user.getUsername().length() < 4 || user.getUsername().length() > 10)
            errors.rejectValue("username", env.getProperty("username.invalid_range"));
        if (userDAO.read(user.getUsername()) != null)
            errors.rejectValue("username", env.getProperty("username.isPresent"));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", env.getProperty("password.not_empty"));
        if (user.getPassword().length() < 6 || user.getPassword().length() > 20)
            errors.rejectValue("password", env.getProperty("password.invalid_range"));

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", env.getProperty("email.not_empty"));
        if (userDAO.isEmailPresent(user.getUsername()))
            errors.rejectValue("email", env.getProperty("email.isPresent"));
        if (!emailValidator.isValid(user.getEmail()))
            errors.rejectValue("email", env.getProperty("email.incorrect_value"));
    }
}
