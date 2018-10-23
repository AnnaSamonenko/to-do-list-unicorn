package com.spring.as.dao;

import com.spring.as.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
public class UserDAOImpl implements GenericDAO<User, String> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List getAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public void update(User u) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void create(User u) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }

    public User read(String username) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        return (User) query.getResultList().get(0);
    }
}
