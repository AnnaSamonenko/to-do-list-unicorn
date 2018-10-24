package com.spring.as.dao;

import com.spring.as.entity.Project;
import com.spring.as.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class ProjectDAOImpl implements GenericDAO<Project, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Project read(Long id) {
        return em.find(Project.class, id);
    }

    @Transactional
    public Project read(String projectName) {
        Query query = em.createQuery(" FROM Project p WHERE p.project_name = :projectName");
        query.setParameter("projectName", projectName);
        return (Project) query.getResultList().get(0);
    }

    @Transactional
    public List<Project> read(User user) {
        Query query = em.createQuery(" FROM Project p WHERE p.username = :username");
        query.setParameter("username", user.getUsername());
        return query.getResultList();
    }

    @Transactional
    @Override
    public void delete(long id) {
        Project project = em.find(Project.class, id);
        em.remove(project);
    }

    @Transactional
    @Override
    public List getAll() {
        Query query = em.createQuery("SELECT p FROM Project p");
        return query.getResultList();
    }

    @Transactional
    @Override
    public void update(Project pr) {
        em.merge(pr);
    }

    @Transactional
    @Override
    public void create(Project pr) {
        em.persist(pr);
    }
}
