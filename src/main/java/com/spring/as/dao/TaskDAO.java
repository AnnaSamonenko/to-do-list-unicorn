package com.spring.as.dao;

import com.spring.as.entity.Task;

import java.util.List;

public interface TaskDAO {

    Task read(long id);

    void update(Task task);

    void create(Task task);

    void delete(String id);

    List<Task> getAllTasks();

}
