package com.spring.as.bootstrap;

import com.spring.as.model.User;
import com.spring.as.repository.ProjectDAO;
import com.spring.as.repository.TaskDAO;
import com.spring.as.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final String EMAIL = "anna_samonenko@gmail.com";
    private static final String PASSWORD = "123456";
    private static final String USERNAME = "anna_samonenko";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
    }

    private void loadUsers() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user1 = User.builder()
                .username(USERNAME)
                .email(EMAIL)
                .password(passwordEncoder.encode(PASSWORD))
                .enabled(true)
                .role("USER_ROLE")
                .build();

        userDAO.create(user1);
    }

}
