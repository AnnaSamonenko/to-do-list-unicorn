package com.spring.as.dao;

import com.spring.as.entity.ProjectDTO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
public class ProjectDAOImpl implements GenericDAO<ProjectDTO> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");

    @Override
    public ProjectDTO read(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(ProjectDTO.class, id);
    }

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        ProjectDTO project = em.find(ProjectDTO.class, id);
        em.getTransaction().begin();
        em.remove(project);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List getAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM ProjectDTO p");
        return query.getResultList();
    }

    @Override
    public void update(ProjectDTO pr) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(pr);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void create(ProjectDTO pr) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pr);
        em.getTransaction().commit();
        em.close();
    }
}
