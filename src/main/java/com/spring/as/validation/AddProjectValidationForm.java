package com.spring.as.validation;

import com.spring.as.entity.Project;
import com.spring.as.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddProjectValidationForm implements Validator {

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Project project = (Project) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", env.getProperty("project.not_empty"));
        if (userService.getAuthorizedUser().getProjects().contains(project))
            errors.rejectValue("name", env.getProperty("project.isPresent"));
    }
}
