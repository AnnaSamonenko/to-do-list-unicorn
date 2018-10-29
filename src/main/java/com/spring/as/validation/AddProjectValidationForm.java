package com.spring.as.validation;

import com.spring.as.dao.ProjectDAOImpl;
import com.spring.as.dao.UserDAOImpl;
import com.spring.as.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddProjectValidationForm implements Validator {

    @Autowired
    private UserDAOImpl userDAO;

    @Autowired
    private ProjectDAOImpl project;

    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
