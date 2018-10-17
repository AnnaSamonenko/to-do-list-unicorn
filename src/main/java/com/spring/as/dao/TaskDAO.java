package com.spring.as.dao;

import com.spring.as.entity.TaskDTO;

public interface TaskDAO extends GenericDAO {

    void update(TaskDTO t);

    void create(TaskDTO t);

}
