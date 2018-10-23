package com.spring.as.service;

import com.spring.as.dao.UserDAOImpl;
import com.spring.as.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAOImpl userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.create(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDAO.getByUsername(s);
    }
}
