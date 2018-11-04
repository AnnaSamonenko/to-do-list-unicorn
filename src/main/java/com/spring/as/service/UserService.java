package com.spring.as.service;

import com.spring.as.dao.UserDAOImpl;
import com.spring.as.entity.User;
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
        return userDAO.read(username);
    }

    public void create(User user) {
        if (userDAO.isUsernamePresent(user.getUsername()))
            throw new IllegalArgumentException("Username is already present");
        if (userDAO.isEmailPresent(user.getEmail()))
            throw new IllegalArgumentException("Email is already present");

        user.setRole("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.create(user);
    }

    public void update(User user) {
        userDAO.update(user);
    }

    public boolean existsByEmail(String email) {
        return userDAO.isEmailPresent(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.read(s);
        if (user == null)
            throw new UsernameNotFoundException("No user found with username " + s);
        return userDAO.read(s);
    }

    public User getAuthorizedUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        return (User) loadUserByUsername(username);
    }

}
