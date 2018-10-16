package com.spring.as;

import com.spring.as.dao.TaskDAO;
import com.spring.as.dao.TaskDAOImpl;
import com.spring.as.entity.TaskDTO;
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
        TaskDTO t = new TaskDTO();
        t.setTitle("Learn Hibernate");
        t.setDescription("Be best");
        t.setDate(LocalDate.now());
        TaskDAO taskDAO = new TaskDAOImpl();
        taskDAO.create(t);

        SpringApplication.run(Application.class, args);
    }
}
