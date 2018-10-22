//package com.spring.as.dao;
//
//import com.spring.as.entity.Role;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//@Component
//public class RoleDAO {
//
//    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");
//
//    public void create(Role role) {
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(role);
//        em.getTransaction().commit();
//        em.close();
//    }
//
//}
