package com.spring.as.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "project", schema = "dei4m2s11duck4")
public class Project {

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
    private List<Task> tasks;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;
}
