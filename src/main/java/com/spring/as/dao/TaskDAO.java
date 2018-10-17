package com.spring.as.dao;

import com.spring.as.entity.TaskDTO;

import java.util.List;

public interface TaskDAO {

    TaskDTO read(long id);

    void update(TaskDTO taskDTO);

    void create(TaskDTO taskDTO);

    void delete(long id);

    List<TaskDTO> getAllTasks();

}
