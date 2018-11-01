package com.spring.as.dto;

import lombok.Getter;
import lombok.Setter;

public class AddTaskDTO {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String projectName;

}
