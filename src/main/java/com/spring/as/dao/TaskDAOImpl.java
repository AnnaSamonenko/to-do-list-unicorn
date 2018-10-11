package com.spring.as.dao;

import com.spring.as.entity.Task;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class TaskDAOImpl implements TaskDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");
    private EntityManager em = emf.createEntityManager();

    @Override
    public Task read(long id) {
        return null;
    }

    @Override
    public void update(Task task) {
    }

    @Override
    public void create(Task task) {
        em.getTransaction().begin();
        em.persist(task);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }
}
