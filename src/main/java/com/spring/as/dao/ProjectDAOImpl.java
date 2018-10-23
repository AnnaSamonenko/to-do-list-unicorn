package com.spring.as.dao;

import com.spring.as.entity.Project;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
public class ProjectDAOImpl implements GenericDAO<Project, Long> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");

    @Override
    public Project read(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Project.class, id);
    }

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        Project project = em.find(Project.class, id);
        em.getTransaction().begin();
        em.remove(project);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List getAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Project p");
        return query.getResultList();
    }

    @Override
    public void update(Project pr) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(pr);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void create(Project pr) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pr);
        em.getTransaction().commit();
        em.close();
    }
}
