package com.spring.as.service;

import com.spring.as.dao.UserDAO;
import com.spring.as.dao.VerificationTokenDAO;
import com.spring.as.model.User;
import com.spring.as.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenDAO tokenDAO;

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @Override
    public User getUser(String username) {
        if (userDAO.read(username) == null)
            throw new UsernameNotFoundException("No user found with username " + username);
        return userDAO.read(username);
    }

    @Override
    public User createAccount(User user) {
        if (userDAO.read(user.getUsername()) != null)
            throw new IllegalArgumentException("User with such username is already present");
        if (userDAO.isEmailPresent(user.getEmail()))
            throw new IllegalArgumentException("Email is already present");

        user.setRole("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.create(user);
        return user;
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void deleteAccount(String username) {
        if (userDAO.read(username) == null)
            throw new UsernameNotFoundException("No user found with username " + username);
        userDAO.delete(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (userDAO.read(username) == null)
            throw new UsernameNotFoundException("No user found with username " + username);
        return userDAO.read(username);
    }

    @Override
    public User getAuthorizedUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        return (User) loadUserByUsername(username);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenDAO.findByToken(VerificationToken);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        tokenDAO.save(verificationToken);
    }
}
