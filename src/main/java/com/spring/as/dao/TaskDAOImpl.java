package com.spring.as.dao;

import com.spring.as.entity.Task;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAOImpl implements GenericDAO<Task> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");

    @Override
    public Task read(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Task.class, id);
    }

    @Override
    public void update(Task taskDTO) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(taskDTO);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void create(Task taskDTO) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(taskDTO);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        Task task = em.find(Task.class, id);
        em.getTransaction().begin();
        em.remove(task);
        em.getTransaction().commit();
        em.close();
    }

    public List<Task> getAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT t FROM Task t");
        return (ArrayList<Task>) query.getResultList();
    }
}
