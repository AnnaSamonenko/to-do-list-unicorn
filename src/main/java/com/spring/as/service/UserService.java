package com.spring.as.service;

import com.spring.as.dao.UserDAOImpl;
import com.spring.as.entity.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAOImpl userDAO;

    public void register(UserDTO user) {
        userDAO.create(user);
    }

    public List<UserDTO> getAllUsers() {
        return userDAO.getAll();
    }

    public UserDTO getUser(long id) {
        return userDAO.read(id);
    }

    public void create(UserDTO user) {
        userDAO.create(user);
    }
}
