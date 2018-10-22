package com.spring.as.service;

import com.spring.as.dao.UserDAOImpl;
import com.spring.as.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAOImpl userDAO;

    public void register(User user) {
        userDAO.create(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    public User getUser(long id) {
        return userDAO.read(id);
    }

    public void create(User user) {
        userDAO.create(user);
    }
}
