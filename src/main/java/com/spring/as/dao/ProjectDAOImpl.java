package com.spring.as.dao;

import com.spring.as.entity.ProjectDTO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Component
public class ProjectDAOImpl implements GenericDAO<ProjectDTO> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");

    @Override
    public ProjectDTO read(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void update(ProjectDTO pr) {

    }

    @Override
    public void create(ProjectDTO pr) {

    }
}
