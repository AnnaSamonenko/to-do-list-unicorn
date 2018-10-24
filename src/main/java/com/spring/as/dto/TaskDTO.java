package com.spring.as.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * from /rest/task/add
 */

public class TaskDTO {

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
