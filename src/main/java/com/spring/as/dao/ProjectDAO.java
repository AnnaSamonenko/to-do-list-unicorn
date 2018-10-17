package com.spring.as.dao;

import com.spring.as.entity.ProjectDTO;

public interface ProjectDAO extends GenericDAO {

    void update(ProjectDTO pr);

    void create(ProjectDTO pr);

}
