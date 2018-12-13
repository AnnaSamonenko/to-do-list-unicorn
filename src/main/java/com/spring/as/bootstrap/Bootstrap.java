package com.spring.as.bootstrap;

import com.spring.as.model.Project;
import com.spring.as.model.Task;
import com.spring.as.model.User;
import com.spring.as.repository.ProjectDAO;
import com.spring.as.repository.TaskDAO;
import com.spring.as.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final String EMAIL = "anna_samonenko@gmail.com";
    private static final String PASSWORD = "123456";
    private static final String USERNAME = "anna_samonenko";
    private static final String PROJECT_NAME_1 = "HOME";
    private static final String PROJECT_NAME_2 = "STUDY";

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
        addProject(user1);
    }

    @Transactional
    private void addProject(User user) {
        Project project1 = Project.builder().name(PROJECT_NAME_1).user(user).build();
        projectDAO.create(project1);
        createTask(project1, "Clean laptop");
        createTask(project1, "Glossary");

        Project project2 = Project.builder().name(PROJECT_NAME_2).user(user).build();
        projectDAO.create(project2);
        createTask(project2, "Learn Java");
        createTask(project2, "Learn Spring");
    }

    @Transactional
    private void createTask(Project project, String title) {
        Task task = Task.builder().title(title)
                .date(LocalDate.now())
                .deadline(LocalDate.now().plusDays(1))
                .project(project)
                .build();

        taskDAO.create(task);
    }

}
