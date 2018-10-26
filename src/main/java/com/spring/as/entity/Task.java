package com.spring.as.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "task", schema = "dei4m2s11duck4")
public class Task {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "task_id")
    private long id;

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

    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
