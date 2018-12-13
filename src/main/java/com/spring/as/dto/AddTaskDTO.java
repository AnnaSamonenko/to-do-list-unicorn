package com.spring.as.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AddTaskDTO {

    @Getter
    @Setter
    @NotBlank(message = "{task.title.not_empty}")
    private String title;

    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate deadline;

    @Getter
    @Setter
    private String projectName;

}
