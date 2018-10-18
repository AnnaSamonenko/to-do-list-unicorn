package com.spring.as.dao;

import com.spring.as.entity.UserDTO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
public class UserDAOImpl implements GenericDAO<UserDTO> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");

    @Override
    public UserDTO read(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(UserDTO.class, id);
    }

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        UserDTO user = em.find(UserDTO.class, id);
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List getAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT u FROM UserDTO u");
        return query.getResultList();
    }

    @Override
    public void update(UserDTO u) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void create(UserDTO u) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }
}
