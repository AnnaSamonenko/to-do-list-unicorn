package com.spring.as.dao;

import com.spring.as.entity.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskDAOImpl implements GenericDAO<Task, Long> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Task read(Long id) {
        return em.find(Task.class, id);
    }

    @Override
    public void update(Task taskDTO) {
        em.merge(taskDTO);
    }

    @Override
    public void create(Task taskDTO) {
        em.persist(taskDTO);
    }

    @Override
    public void delete(long id) {
        Task task = em.find(Task.class, id);
        em.remove(task);
    }

    public List<Task> getAll() {
        Query query = em.createQuery("SELECT t FROM Task t");
        return query.getResultList();
    }
}
