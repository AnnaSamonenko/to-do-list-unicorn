package com.spring.as.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "task")
public class Task {

    @Getter
    @Setter
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id")
    private String id;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;

    @Getter
    @Setter
    @Column(name = "description")
    private String description;

    @Getter
    @Setter
    @Column(name = "date_of_creation")
    private LocalDate date;

}
