package com.spring.as.dao;

import com.spring.as.entity.TaskDTO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAOImpl implements GenericDAO<TaskDTO> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");

    @Override
    public TaskDTO read(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(TaskDTO.class, id);
    }

    @Override
    public void update(TaskDTO taskDTO) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(taskDTO);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void create(TaskDTO taskDTO) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(taskDTO);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        TaskDTO task = em.find(TaskDTO.class, id);
        em.getTransaction().begin();
        em.remove(task);
        em.getTransaction().commit();
        em.close();
    }

    public List<TaskDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT t FROM TaskDTO t");
        return (ArrayList<TaskDTO>) query.getResultList();
    }
}
