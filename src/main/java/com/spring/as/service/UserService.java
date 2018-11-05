package com.spring.as.service;

import com.spring.as.dao.UserDAOImpl;
import com.spring.as.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    public User getUser(String username) {
        if (userDAO.read(username) == null)
            throw new UsernameNotFoundException("No user found with username " + username);
        return userDAO.read(username);
    }

    public void create(User user) {
        if (userDAO.read(user.getUsername()) != null)
            throw new IllegalArgumentException("User with such username is already present");
        if (userDAO.isEmailPresent(user.getEmail()))
            throw new IllegalArgumentException("Email is already present");

        user.setRole("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.create(user);
    }

    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (userDAO.read(username) == null)
            throw new UsernameNotFoundException("No user found with username " + username);
        return userDAO.read(username);
    }

    public User getAuthorizedUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        return (User) loadUserByUsername(username);
    }

}
