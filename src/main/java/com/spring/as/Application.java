package com.spring.as;

import com.spring.as.dao.TaskDAOImpl;
import com.spring.as.entity.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;

@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        Task t = new Task();
        t.setTitle("Learn Hibernate");
        t.setDescription("Be best");
        t.setDate(LocalDate.now());
        TaskDAOImpl taskDAO = new TaskDAOImpl();
        taskDAO.create(t);

        SpringApplication.run(Application.class, args);
    }
}
