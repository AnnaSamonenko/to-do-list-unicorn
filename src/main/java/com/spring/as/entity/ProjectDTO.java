package com.spring.as.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "project")
public class ProjectDTO {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "project_id")
    private long id;

    @Getter
    @Setter
    @Column(name = "project_name")
    private String name;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private List<TaskDTO> tasks;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDTO user;
}
