package com.spring.as.dao;

import com.spring.as.entity.Project;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class ProjectDAOImpl implements GenericDAO<Project, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Project read(Long id) {
        return em.find(Project.class, id);
    }

    @Override
    public void delete(long id) {
        Project project = em.find(Project.class, id);
        em.remove(project);
    }

    @Override
    public List getAll() {
        Query query = em.createQuery("SELECT p FROM Project p");
        return query.getResultList();
    }

    @Override
    public void update(Project pr) {
        em.merge(pr);
    }

    @Override
    public void create(Project pr) {
        em.persist(pr);
    }
}
