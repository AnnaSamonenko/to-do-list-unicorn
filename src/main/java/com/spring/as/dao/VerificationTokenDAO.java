package com.spring.as.dao;

import com.spring.as.model.VerificationToken;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

//TODO: implement me! (ps VerificationTokenDAO)
@Repository
@Transactional
public class VerificationTokenDAO {

    @PersistenceContext
    private EntityManager em;

    public VerificationToken findByToken(String token) {
        Query query = em.createQuery("FROM VerificationToken t WHERE t.token = :token");
        query.setParameter("token", token);
        return (VerificationToken) query.getResultList().get(0);
    }

//    public VerificationToken findByUser() {
//        return null;
//    }

    public void save(VerificationToken verificationToken) {
        em.persist(verificationToken);
    }

}
