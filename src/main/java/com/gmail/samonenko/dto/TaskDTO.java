package com.gmail.samonenko.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class TaskDTO {

    @NotBlank(message = "{task.title.not_empty}")
    private String title;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate deadline;

    private String projectName;

}
