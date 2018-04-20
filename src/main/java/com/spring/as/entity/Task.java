package com.spring.as.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Task")
public class Task {

    @Getter
    @Setter
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "taskId")
    private String id;

    @Getter
    @Setter
    @Column(name = "taskTitle")
    private String title;

    @Getter
    @Setter
    @Column(name = "taskDescription")
    private String description;

    @Getter
    @Setter
    @Column(name = "taskDate")
    private Date date;

}
