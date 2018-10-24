package com.spring.as.dao;

import com.spring.as.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDAOImpl implements GenericDAO<User, String> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public List getAll() {
        Query query = em.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public void update(User u) {
        em.merge(u);
    }

    @Override
    @Transactional
    public void create(User u) {
        em.persist(u);
    }

    @Override
    public User read(String username) {
        Query query = em.createQuery("FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        return (User) query.getResultList().get(0);
    }
}
