package com.spring.as.dao;

import com.spring.as.entity.Project;
import com.spring.as.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProjectDAOImpl implements GenericDAO<Project, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Project read(Long id) {
        return em.find(Project.class, id);
    }

    public Project read(String projectName) {
        Query query = em.createQuery(" FROM Project p WHERE p.project_name = :projectName");
        query.setParameter("projectName", projectName);
        return (Project) query.getResultList().get(0);
    }

    public List<Project> read(User user) {
        Query query = em.createQuery("FROM Project p WHERE p.user.username = :username");
        query.setParameter("username", user.getUsername());
        return query.getResultList();
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
