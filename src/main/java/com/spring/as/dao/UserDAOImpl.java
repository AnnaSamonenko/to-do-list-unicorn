package com.spring.as.dao;

import com.spring.as.entity.UserDTO;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Component
public class UserDAOImpl implements GenericDAO<UserDTO> {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("config-unit");

    @Override
    public UserDTO read(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void update(UserDTO u) {

    }

    @Override
    public void create(UserDTO u) {

    }
}
