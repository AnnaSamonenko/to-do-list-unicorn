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
public class TaskDAOImpl implements TaskDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");
    private EntityManager em = emf.createEntityManager();

    @Override
    public TaskDTO read(long id) {
        return null;
    }

    @Override
    public void update(TaskDTO taskDTO) {
    }

    @Override
    public void create(TaskDTO taskDTO) {
        em.getTransaction().begin();
        em.persist(taskDTO);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(String id) {

    }

    public List<TaskDTO> getAllTasks() {
        Query query = em.createQuery("SELECT t FROM TaskDTO t");
        return (ArrayList<TaskDTO>) query.getResultList();
    }
}
