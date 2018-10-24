package com.spring.as.dao;

import com.spring.as.entity.Task;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAOImpl implements GenericDAO<Task, Long> {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Task read(Long id) {
        return em.find(Task.class, id);
    }

    @Transactional
    @Override
    public void update(Task taskDTO) {
        em.merge(taskDTO);
    }

    @Transactional
    @Override
    public void create(Task taskDTO) {
        em.persist(taskDTO);
    }

    @Transactional
    @Override
    public void delete(long id) {
        Task task = em.find(Task.class, id);
        em.remove(task);
    }

    @Transactional
    public List<Task> getAll() {
        Query query = em.createQuery("SELECT t FROM Task t");
        return (ArrayList<Task>) query.getResultList();
    }
}
