package com.spring.as.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "project_id")
    private long id;

    @Column(name = "project_name")
    @NotBlank(message = "{project.name.not_empty}")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private List<Task> tasks;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
