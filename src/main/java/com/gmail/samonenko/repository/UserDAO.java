package com.gmail.samonenko.repository;

import com.gmail.samonenko.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDAO implements GenericDAO<User, String> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(String id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public List getAll() {
        Query query = em.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public User update(User u) {
        return em.merge(u);
    }

    @Override
    public void create(User u) {
        em.persist(u);
    }

    @Override
    public User read(String username) {
        return em.find(User.class, username);
    }

    public boolean isEmailPresent(String email) {
        Query query = em.createQuery("SELECT u FROM User u WHERE email = :email");
        List<User> users = query.setParameter("email", email).getResultList();
        return !users.isEmpty();
    }

}
