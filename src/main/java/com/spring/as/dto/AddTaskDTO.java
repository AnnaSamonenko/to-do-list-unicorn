package com.spring.as.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class AddTaskDTO {

    @Getter
    @Setter
    @NotBlank(message = "{task.title.not_empty}")
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String projectName;

}
